package com.mitchell.claims.dao;

import org.springframework.data.repository.Repository;
import com.mitchell.claims.domain.Claim;
import java.util.Date;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
public interface ClaimRepository extends Repository<Claim, Long> {
    List<Claim> findAll();

    List<Claim> findByLossDateBetween(Date lossDateFrom, Date lossDateTo);

    Claim findById(Long id);

    Claim save(Claim claim);
}
