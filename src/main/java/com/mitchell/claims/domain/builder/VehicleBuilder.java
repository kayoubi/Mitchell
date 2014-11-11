package com.mitchell.claims.domain.builder;

import com.mitchell.claims.domain.Vehicle;

/**
 * @author Khaled Ayoubi
 */
public class VehicleBuilder {
    private Vehicle vehicle;

    public VehicleBuilder() {
        vehicle = new Vehicle();
    }

    public VehicleBuilder withId(Long id) {
        vehicle.setId(id);
        return this;
    }

    public VehicleBuilder withVin(String vin) {
        vehicle.setVin(vin);
        return this;
    }

    public VehicleBuilder withModelYear(Integer year) {
        vehicle.setModelYear(year);
        return this;
    }

    public Vehicle build() {
        return vehicle;
    }
}
