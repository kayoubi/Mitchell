package com.mitchell.claims.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LossInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String causeOfLoss;
    private Date reportedDate;
    private String lossDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCauseOfLoss() {
        return causeOfLoss;
    }

    public void setCauseOfLoss(String causeOfLoss) {
        this.causeOfLoss = causeOfLoss;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getLossDescription() {
        return lossDescription;
    }

    public void setLossDescription(String lossDescription) {
        this.lossDescription = lossDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LossInfo)) return false;

        LossInfo lossInfo = (LossInfo) o;

        if (causeOfLoss != null ? !causeOfLoss.equals(lossInfo.causeOfLoss) : lossInfo.causeOfLoss != null)
            return false;
        if (id != null ? !id.equals(lossInfo.id) : lossInfo.id != null) return false;
        if (lossDescription != null ? !lossDescription.equals(lossInfo.lossDescription) : lossInfo.lossDescription != null)
            return false;
        if (reportedDate != null ? !reportedDate.equals(lossInfo.reportedDate) : lossInfo.reportedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (causeOfLoss != null ? causeOfLoss.hashCode() : 0);
        result = 31 * result + (reportedDate != null ? reportedDate.hashCode() : 0);
        result = 31 * result + (lossDescription != null ? lossDescription.hashCode() : 0);
        return result;
    }
}
