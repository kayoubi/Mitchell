package com.mitchell.claims.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.domain.builder.ClaimBuilder;
import com.mitchell.claims.domain.builder.LossInfoBuilder;
import com.mitchell.claims.domain.builder.VehicleBuilder;
import com.mitchell.claims.service.ClaimService;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Khaled Ayoubi
 */
public class ClaimsControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private ClaimsController claimsController;

    @Mock
    private ClaimService claimService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        ExceptionHandlerExceptionResolver exceptionResolver =  new ExceptionHandlerExceptionResolver() {
            @Override
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(GlobalController.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(new GlobalController(), method);
            }
        };
        exceptionResolver.afterPropertiesSet();

        this.mockMvc = MockMvcBuilders.standaloneSetup(claimsController)
                        .setHandlerExceptionResolvers(exceptionResolver)
                        .build();
    }

    @Test
    public void testSearch() throws Exception {
        this.mockMvc.perform(get("/mitchell/claims?lossDateFrom=2014-07-10&lossDateTo=2014-07-11").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).search(new SimpleDateFormat("yyyy-MM-dd").parse("2014-07-10"), new SimpleDateFormat("yyyy-MM-dd").parse("2014-07-11"));
    }

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/mitchell/claims/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).get(1L);
    }

    @Test
    public void testCreate() throws Exception {
        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>khaled</cla:claimantFirstName>" +
                            "<cla:vehicles>" +
                                "<cla:vehicleDetails>" +
                                    "<cla:vin>3Ue</cla:vin>" +
                                "</cla:vehicleDetails>" +
                            "</cla:vehicles>" +
                            "<cla:lossInfo>" +
                                "<cla:causeOfLoss>Collision</cla:causeOfLoss>" +
                            "</cla:lossInfo>" +
                        "</cla:MitchellClaim>";
        this.mockMvc.perform(post("/mitchell/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));

        Claim c = new ClaimBuilder().withFirstName("khaled")
                                    .withVehicle(new VehicleBuilder().withVin("3Ue").build())
                                    .withLossInf(new LossInfoBuilder().withCauseOfLoss("Collision").build())
                                    .build();
        verify(claimService).create(c);
    }

    @Test
    public void testCreateWithInvalidContentThrowsException() throws Exception {
        String body = "BAD CONTENT";
        this.mockMvc.perform(post("/mitchell/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(400))
                .andExpect(content().string(GlobalController.MARSHALLING));
    }

    @Test
    public void testCreateWithNoVehicleThrowsException() throws Exception {
        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>khaled</cla:claimantFirstName>" +
                         "</cla:MitchellClaim>";
        this.mockMvc.perform(post("/mitchell/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(400))
                .andExpect(content().string(GlobalController.VALIDATION));
    }

    @Test
    public void testCreateWithInvalidLossCodeThrowsException() throws Exception {
        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>khaled</cla:claimantFirstName>" +
                            "<cla:vehicles>" +
                                "<cla:vehicleDetails>" +
                                    "<cla:vin>3Ue</cla:vin>" +
                                "</cla:vehicleDetails>" +
                            "</cla:vehicles>" +
                            "<cla:lossInfo>" +
                                "<cla:causeOfLoss>Invalid Code</cla:causeOfLoss>" +
                            "</cla:lossInfo>" +
                        "</cla:MitchellClaim>";
        this.mockMvc.perform(post("/mitchell/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(400))
                .andExpect(content().string(GlobalController.VALIDATION));
    }

    @Test
    public void testUpdate() throws Exception {
        when(claimService.get(anyLong())).thenReturn(new ClaimBuilder().withId(1L).withFirstName("khaled").build());

        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>test</cla:claimantFirstName>" +
                        "</cla:MitchellClaim>";
        this.mockMvc.perform(put("/mitchell/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new ClaimBuilder().withId(1L).withFirstName("test").build());
    }

    @Test
    public void testUpdateNullNotUpdated() throws Exception {
        Date now = new Date();
        when(claimService.get(anyLong())).thenReturn(new ClaimBuilder().withId(1L).withLossDate(now).build());

        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>test</cla:claimantFirstName>" +
                        "</cla:MitchellClaim>";
        this.mockMvc.perform(put("/mitchell/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new ClaimBuilder().withId(1L).withFirstName("test").withLossDate(now).build());
    }

    @Test
    public void testUpdateNullNotUpdatedInChildVehicle() throws Exception {
        Date now = new Date();
        when(claimService.get(anyLong())).thenReturn(
                new ClaimBuilder()
                        .withId(1L)
                        .withLossDate(now)
                        .withVehicle(new VehicleBuilder().withModelYear(2014).build())
                        .build());

        String body =   "<cla:MitchellClaim xmlns:cla=\"http://www.mitchell.com/examples/claim\">" +
                            "<cla:claimantFirstName>test</cla:claimantFirstName>" +
                        "</cla:MitchellClaim>";
        this.mockMvc.perform(put("/mitchell/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new ClaimBuilder().withId(1L).withFirstName("test")
                .withLossDate(now).withVehicle(new VehicleBuilder().withModelYear(2014).build()).build());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/mitchell/claims/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).delete(1L);
    }

    @Test
    public void testGetVehicle() throws Exception {
        this.mockMvc.perform(get("/mitchell/claims/1/vehicles/2").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
    }
}
