package com.mitchell.claims.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mitchell.claims.Application;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.domain.builder.ClaimBuilder;
import com.mitchell.claims.domain.builder.VehicleBuilder;

import static org.junit.Assert.*;


/**
 * @author Khaled Ayoubi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:/sampleData.xml")
@ContextConfiguration(classes = {Application.class})
@Transactional
public class ClaimServiceIntegrationTest {
    @Autowired
    private ClaimService claimService;

    @Test
    public void testGetClaim() {
        Claim claim = claimService.get(1L);
        assertEquals(1, claim.getId().intValue());
        assertEquals(5391224937543113779L, claim.getClaimNumber().longValue());
        assertEquals(2, claim.getVehicles().size());
        assertEquals("vin1-1", claim.getVehicles().get(0).getVin());
        assertEquals("vin1-2", claim.getVehicles().get(1).getVin());
    }

    @Test
    public void testDelete() {
        assertNotNull(claimService.get(1L));
        claimService.delete(1L);
        assertNull(claimService.get(1L));
    }

    @Test
    public void testUpdateClaim() {
        Claim claim = claimService.get(1L);
        assertNotNull(claim);
        assertNull(claim.getClaimantFirstName());
        claim.setClaimantFirstName("khaled");
        claimService.update(claim);

        claim = claimService.get(1L);
        assertEquals("khaled", claim.getClaimantFirstName());
    }

    @Test
    public void testUpdateVehicle() {
        Claim claim = claimService.get(1L);
        assertNotNull(claim);
        assertNotNull(claim.getVehicles().get(0));
        assertEquals("vin1-1", claim.getVehicles().get(0).getVin());

        claim.getVehicles().get(0).setVin("new vin");
        claimService.update(claim);

        claim = claimService.get(1L);
        assertEquals("new vin", claim.getVehicles().get(0).getVin());
    }

    @Test
    public void testCreate() {
        assertEquals(1, claimService.getAll().size());
        Claim claim = new ClaimBuilder().withFirstName("khaled")
                                        .withClaimNumber(345L)
                                        .withVehicle(new VehicleBuilder().withVin("vin").withModelYear(2004).build())
                                        .build();
        claimService.create(claim);
        assertEquals(2, claimService.getAll().size());
        Claim newClaim = claimService.getAll().get(1);
        assertEquals("khaled", newClaim.getClaimantFirstName());
        assertEquals(345, newClaim.getClaimNumber().longValue());
        assertEquals(1, newClaim.getVehicles().size());
        assertEquals("vin", newClaim.getVehicles().get(0).getVin());
        assertEquals(2004, newClaim.getVehicles().get(0).getModelYear().intValue());
    }
}
