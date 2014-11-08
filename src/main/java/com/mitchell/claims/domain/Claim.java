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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;

        Claim claim = (Claim) o;

        if (assignedAdjusterID != null ? !assignedAdjusterID.equals(claim.assignedAdjusterID) : claim.assignedAdjusterID != null)
            return false;
        if (claimNumber != null ? !claimNumber.equals(claim.claimNumber) : claim.claimNumber != null) return false;
        if (claimStatus != claim.claimStatus) return false;
        if (claimantFirstName != null ? !claimantFirstName.equals(claim.claimantFirstName) : claim.claimantFirstName != null)
            return false;
        if (claimantLastName != null ? !claimantLastName.equals(claim.claimantLastName) : claim.claimantLastName != null)
            return false;
        if (id != null ? !id.equals(claim.id) : claim.id != null) return false;
        if (lossDate != null ? !lossDate.equals(claim.lossDate) : claim.lossDate != null) return false;
        if (lossInfo != null ? !lossInfo.equals(claim.lossInfo) : claim.lossInfo != null) return false;
        if (name != null ? !name.equals(claim.name) : claim.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (claimNumber != null ? claimNumber.hashCode() : 0);
        result = 31 * result + (claimantFirstName != null ? claimantFirstName.hashCode() : 0);
        result = 31 * result + (claimantLastName != null ? claimantLastName.hashCode() : 0);
        result = 31 * result + (claimStatus != null ? claimStatus.hashCode() : 0);
        result = 31 * result + (lossDate != null ? lossDate.hashCode() : 0);
        result = 31 * result + (lossInfo != null ? lossInfo.hashCode() : 0);
        result = 31 * result + (assignedAdjusterID != null ? assignedAdjusterID.hashCode() : 0);
        return result;
    }
}
