package com.mitchell.claims.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.domain.Vehicle;
import com.mitchell.claims.domain.builder.ClaimBuilder;
import com.mitchell.claims.domain.builder.LossInfoBuilder;
import com.mitchell.claims.domain.builder.VehicleBuilder;
import com.mitchell.claims.service.ClaimService;
import com.mitchell.claims.web.dto.ClaimsList;
import com.mitchell.claims.web.helper.NullAwareBeanUtilsBean;
import javax.validation.Valid;
import java.util.Date;
import java.util.Random;

/**
 * @author Khaled Ayoubi
 */

@RestController
@RequestMapping("/claims")
public class ClaimsController {

    @Autowired
    private ClaimService claimService;

//    @RequestMapping(method = RequestMethod.GET)
//    public ClaimsList getAll() {
//        return new ClaimsList(claimService.getAll());
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ClaimsList search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date lossDateFrom, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date lossDateTo) {
        return new ClaimsList(claimService.search(lossDateFrom, lossDateTo == null ? new Date() : lossDateTo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Claim get(@PathVariable Long id) {
        return claimService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Claim create(@RequestBody @Valid Claim claim) {
            claim.setId(null);
            return claimService.create(claim);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Claim update(@PathVariable Long id, @RequestBody Claim claim) throws Exception {
        claim.setId(id);

        Claim original = claimService.get(id);
        new NullAwareBeanUtilsBean().copyProperties(original, claim);
        return claimService.update(original);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        claimService.delete(id);
    }

    @RequestMapping(value = "/{claimId}/vehicles/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable Long claimId, @PathVariable Long id) {
        Claim claim = claimService.get(claimId);
        if (claim != null) {
            for (Vehicle vehicle : claim.getVehicles()) {
                if (vehicle.getId().equals(id)) {
                    return vehicle;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/fixture", method = RequestMethod.GET)
    public String fixture() {
        for (long i = 0; i < 5; i++) {
            Claim c = new ClaimBuilder()
                        .withClaimNumber(new Random().nextLong())
                        .withLossDate(new Date())
                        .withVehicle(new VehicleBuilder().withVin(("vin " + i)).build())
                        .withVehicle(new VehicleBuilder().withVin(("vin2 " + i)).build())
                        .withLossInf(new LossInfoBuilder().withCauseOfLoss("Collision").build())
                        .build();
            claimService.create(c);
        }
        return "DONE!";
    }
}
