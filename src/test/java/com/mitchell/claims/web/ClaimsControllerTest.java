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
import com.mitchell.claims.service.ClaimService;

import java.text.SimpleDateFormat;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Khaled Ayoubi
 *
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
        String body = "<claim><name>khaled</name></claim>";
        this.mockMvc.perform(post("/claims").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));

        verify(claimService).create(new Claim(null, "khaled"));
    }

    @Test
    public void testUpdate() throws Exception {
        String body = "<claim><id>1</id><name>test</name></claim>";
        this.mockMvc.perform(put("/claims/1").content(body).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).update(new Claim(1L, "test"));
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete("/claims/1").accept(MediaType.APPLICATION_XML))
                .andExpect(status().is(200));
        verify(claimService).delete(1L);
    }
}
