package com.mitchell.claims.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Khaled Ayoubi
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Claim implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long claimNumber;
    private String claimantFirstName;
    private String claimantLastName;
    private ClaimStatus claimStatus;
    private Date lossDate;
    @OneToOne
    private LossInfo lossInfo;
    private Long assignedAdjusterID;
    @OneToMany
    private Set<VehicleDetails> vehicles;

    protected Claim() {
    }

    public Claim(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
