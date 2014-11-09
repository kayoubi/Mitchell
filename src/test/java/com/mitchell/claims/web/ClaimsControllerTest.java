package com.mitchell.claims.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.domain.builder.ClaimBuilder;
import com.mitchell.claims.domain.builder.VehicleBuilder;
import com.mitchell.claims.service.ClaimService;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(claimsController).build();
    }

    @Test
    public void testSearch() throws Exception {
        this.mockMvc.perform(get("/claims?lossDateFrom=2014-07-10&lossDateTo=2014-07-11").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).search(new SimpleDateFormat("yyyy-MM-dd").parse("2014-07-10"), new SimpleDateFormat("yyyy-MM-dd").parse("2014-07-11"));
    }

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/claims/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).get(1L);
    }

    @Test
    public void testCreate() throws Exception {
        String body =   "<claim>" +
                            "<claimantFirstName>khaled</claimantFirstName>" +
                            "<vehicles>" +
                                "<vehicleDetails>" +
                                    "<vin>3Ue</vin>" +
                                "</vehicleDetails>" +
                            "</vehicles>" +
                        "</claim>";
        this.mockMvc.perform(post("/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));

        Claim c = new ClaimBuilder().withFirstName("khaled").withVehicle(new VehicleBuilder().withVin("3Ue").build()).build();
        verify(claimService).create(c);
    }

    @Test
    public void testCreateWithNoVehicleThrowsException() throws Exception {
        String body =   "<claim>" +
                            "<claimantFirstName>khaled</claimantFirstName>" +
                         "</claim>";
        this.mockMvc.perform(post("/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(400));
    }

    @Test
    public void testUpdate() throws Exception {
        when(claimService.get(anyLong())).thenReturn(new ClaimBuilder().withId(1L).withFirstName("khaled").build());

        String body = "<claim><id>1</id><claimantFirstName>test</claimantFirstName></claim>";
        this.mockMvc.perform(put("/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new ClaimBuilder().withId(1L).withFirstName("test").build());
    }

    @Test
    public void testUpdateNullNotUpdated() throws Exception {
        Date now = new Date();
        when(claimService.get(anyLong())).thenReturn(new ClaimBuilder().withId(1L).withLossDate(now).build());

        String body = "<claim><id>1</id><claimantFirstName>test</claimantFirstName></claim>";
        this.mockMvc.perform(put("/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
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

        String body = "<claim><id>1</id><claimantFirstName>test</claimantFirstName></claim>";
        this.mockMvc.perform(put("/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new ClaimBuilder().withId(1L).withFirstName("test")
                .withLossDate(now).withVehicle(new VehicleBuilder().withModelYear(2014).build()).build());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/claims/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).delete(1L);
    }

    @Test
    public void testGetVehicle() throws Exception {
        this.mockMvc.perform(get("/claims/1/vehicles/2").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
    }
}
