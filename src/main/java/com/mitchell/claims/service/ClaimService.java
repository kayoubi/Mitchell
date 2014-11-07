package com.mitchell.claims.service;

import com.mitchell.claims.domain.Claim;
import java.util.Date;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
public interface ClaimService {
    List<Claim> getAll();

    List<Claim> search(Date lossDateFrom, Date lossDateTo);

    Claim get(Long id);

    Claim create(Claim claim);

    Claim update(Claim claim);
}
