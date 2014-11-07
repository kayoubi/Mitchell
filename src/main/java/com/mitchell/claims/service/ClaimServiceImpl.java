package com.mitchell.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.mitchell.claims.dao.ClaimRepository;
import com.mitchell.claims.domain.Claim;

/**
 * @author Khaled Ayoubi
 */
@Transactional
@Component("claimService")
public class ClaimServiceImpl implements ClaimService {
    private ClaimRepository claimRepository;

    @Autowired
    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Claim get(Long id) {
        return claimRepository.findById(id);
    }

    @Override
    public Claim create(Claim claim) {
        return claimRepository.save(claim);
    }
}
