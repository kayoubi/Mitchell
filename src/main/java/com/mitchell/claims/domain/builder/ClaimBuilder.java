package com.mitchell.claims.domain.builder;

import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.domain.LossInfo;
import com.mitchell.claims.domain.Vehicle;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */
public class ClaimBuilder {
    private Claim claim;

    public ClaimBuilder() {
        claim = new Claim();
    }

    public ClaimBuilder withId(Long id) {
        claim.setId(id);
        return this;
    }

    public ClaimBuilder withClaimNumber(Long number) {
        claim.setClaimNumber(number);
        return this;
    }

    public ClaimBuilder withFirstName(String firstName) {
        claim.setClaimantFirstName(firstName);
        return this;
    }

    public ClaimBuilder withLossDate(Date date) {
        claim.setLossDate(date);
        return this;
    }

    public ClaimBuilder withVehicle(Vehicle vehicle) {
        if (claim.getVehicles() == null) {
            claim.setVehicles(new ArrayList<Vehicle>());
        }
        claim.getVehicles().add(vehicle);
        return this;
    }

    public ClaimBuilder withLossInf(LossInfo lossInfo) {
        claim.setLossInfo(lossInfo);
        return this;
    }

    public Claim build() {
        return claim;
    }
}
