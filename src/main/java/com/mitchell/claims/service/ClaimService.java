package com.mitchell.claims.service;

import com.mitchell.claims.domain.Claim;

/**
 * @author Khaled Ayoubi
 */
public interface ClaimService {
    Claim get(Long id);

    Claim create(Claim claim);
}
