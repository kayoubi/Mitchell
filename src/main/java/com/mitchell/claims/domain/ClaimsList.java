package com.mitchell.claims.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
@XmlRootElement(name = "MitchellClaims")
public class ClaimsList {
    private List<Claim> claims;

    protected ClaimsList() {
    }

    public ClaimsList(List<Claim> claims) {
        this.claims = claims;
    }

    @XmlElement(name = "MitchellClaim")
    public List<Claim> getClaims() {
        return claims;
    }
}
