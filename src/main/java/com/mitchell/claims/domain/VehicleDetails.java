package com.mitchell.claims.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */
@Entity
public class VehicleDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String vin;
    @Column
    private Integer modelYear;
    @Column
    private String makeDescription;
    @Column
    private String modelDescription;
    @Column
    private String engineDescription;
    @Column
    private String exteriorColor;
    @Column
    private String licPlate;
    @Column
    private String licPlateState;
    @Column
    private Date licPlateExpDate;
    @Column
    private String damageDescription;
    @Column
    private Long mileage;
}
