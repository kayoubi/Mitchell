package com.mitchell.claims.domain;

import org.hibernate.validator.constraints.NotEmpty;
import com.mitchell.claims.domain.validator.ValidLossInfo;
import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
@Entity
@XmlRootElement(name = "MitchellClaim")
@XmlAccessorType(XmlAccessType.FIELD)
public class Claim implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long claimNumber;
    private String claimantFirstName;
    private String claimantLastName;
    private ClaimStatus claimStatus;
    @Past
    private Date lossDate;
    @ValidLossInfo
    @OneToOne(cascade = {CascadeType.ALL})
    private LossInfo lossInfo;
    private Long assignedAdjusterID;
    @NotEmpty
    @XmlElementWrapper(name="vehicles")
    @XmlElement(name="vehicleDetails")
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Vehicle> vehicles;

    public Claim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Long claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimantFirstName() {
        return claimantFirstName;
    }

    public void setClaimantFirstName(String claimantFirstName) {
        this.claimantFirstName = claimantFirstName;
    }

    public String getClaimantLastName() {
        return claimantLastName;
    }

    public void setClaimantLastName(String claimantLastName) {
        this.claimantLastName = claimantLastName;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public Date getLossDate() {
        return lossDate;
    }

    public void setLossDate(Date lossDate) {
        this.lossDate = lossDate;
    }

    public LossInfo getLossInfo() {
        return lossInfo;
    }

    public void setLossInfo(LossInfo lossInfo) {
        this.lossInfo = lossInfo;
    }

    public Long getAssignedAdjusterID() {
        return assignedAdjusterID;
    }

    public void setAssignedAdjusterID(Long assignedAdjusterID) {
        this.assignedAdjusterID = assignedAdjusterID;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
        if (vehicles != null ? !vehicles.equals(claim.vehicles) : claim.vehicles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (claimNumber != null ? claimNumber.hashCode() : 0);
        result = 31 * result + (claimantFirstName != null ? claimantFirstName.hashCode() : 0);
        result = 31 * result + (claimantLastName != null ? claimantLastName.hashCode() : 0);
        result = 31 * result + (claimStatus != null ? claimStatus.hashCode() : 0);
        result = 31 * result + (lossDate != null ? lossDate.hashCode() : 0);
        result = 31 * result + (lossInfo != null ? lossInfo.hashCode() : 0);
        result = 31 * result + (assignedAdjusterID != null ? assignedAdjusterID.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", claimNumber=" + claimNumber +
                ", claimantFirstName='" + claimantFirstName + '\'' +
                ", claimantLastName='" + claimantLastName + '\'' +
                ", claimStatus=" + claimStatus +
                ", lossDate=" + lossDate +
                ", lossInfo=" + lossInfo +
                ", assignedAdjusterID=" + assignedAdjusterID +
                ", vehicles=" + vehicles +
                '}';
    }
}
