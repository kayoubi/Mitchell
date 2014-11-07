package com.mitchell.claims.dao;

import org.springframework.data.repository.Repository;
import com.mitchell.claims.domain.Claim;
import java.util.List;

/**
 * @author Khaled Ayoubi
 */
public interface ClaimRepository extends Repository<Claim, Long> {
    List<Claim> findAll();

    Claim findById(Long id);

    Claim save(Claim claim);
}
