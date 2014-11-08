package com.mitchell.claims.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */
@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    private String vin;
    private Integer modelYear;
    private String makeDescription;
    private String modelDescription;
    private String engineDescription;
    private String exteriorColor;
    private String licPlate;
    private String licPlateState;
    private Date licPlateExpDate;
    private String damageDescription;
    private Long mileage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;

        Vehicle that = (Vehicle) o;

        if (damageDescription != null ? !damageDescription.equals(that.damageDescription) : that.damageDescription != null)
            return false;
        if (engineDescription != null ? !engineDescription.equals(that.engineDescription) : that.engineDescription != null)
            return false;
        if (exteriorColor != null ? !exteriorColor.equals(that.exteriorColor) : that.exteriorColor != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (licPlate != null ? !licPlate.equals(that.licPlate) : that.licPlate != null) return false;
        if (licPlateExpDate != null ? !licPlateExpDate.equals(that.licPlateExpDate) : that.licPlateExpDate != null)
            return false;
        if (licPlateState != null ? !licPlateState.equals(that.licPlateState) : that.licPlateState != null)
            return false;
        if (makeDescription != null ? !makeDescription.equals(that.makeDescription) : that.makeDescription != null)
            return false;
        if (mileage != null ? !mileage.equals(that.mileage) : that.mileage != null) return false;
        if (modelDescription != null ? !modelDescription.equals(that.modelDescription) : that.modelDescription != null)
            return false;
        if (modelYear != null ? !modelYear.equals(that.modelYear) : that.modelYear != null) return false;
        if (vin != null ? !vin.equals(that.vin) : that.vin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (vin != null ? vin.hashCode() : 0);
        result = 31 * result + (modelYear != null ? modelYear.hashCode() : 0);
        result = 31 * result + (makeDescription != null ? makeDescription.hashCode() : 0);
        result = 31 * result + (modelDescription != null ? modelDescription.hashCode() : 0);
        result = 31 * result + (engineDescription != null ? engineDescription.hashCode() : 0);
        result = 31 * result + (exteriorColor != null ? exteriorColor.hashCode() : 0);
        result = 31 * result + (licPlate != null ? licPlate.hashCode() : 0);
        result = 31 * result + (licPlateState != null ? licPlateState.hashCode() : 0);
        result = 31 * result + (licPlateExpDate != null ? licPlateExpDate.hashCode() : 0);
        result = 31 * result + (damageDescription != null ? damageDescription.hashCode() : 0);
        result = 31 * result + (mileage != null ? mileage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", modelYear=" + modelYear +
                ", makeDescription='" + makeDescription + '\'' +
                ", modelDescription='" + modelDescription + '\'' +
                ", engineDescription='" + engineDescription + '\'' +
                ", exteriorColor='" + exteriorColor + '\'' +
                ", licPlate='" + licPlate + '\'' +
                ", licPlateState='" + licPlateState + '\'' +
                ", licPlateExpDate=" + licPlateExpDate +
                ", damageDescription='" + damageDescription + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
