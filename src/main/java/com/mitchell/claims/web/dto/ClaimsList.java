package com.mitchell.claims.web.dto;

import com.mitchell.claims.domain.Claim;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
@XmlRootElement(name = "claims")
public class ClaimsList {
    private List<Claim> claims;

    protected ClaimsList() {
    }

    public ClaimsList(List<Claim> claims) {
        this.claims = claims;
    }

    @XmlElement(name = "claim")
    public List<Claim> getClaims() {
        return claims;
    }
}
