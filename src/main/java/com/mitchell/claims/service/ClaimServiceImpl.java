package com.mitchell.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.mitchell.claims.dao.ClaimRepository;
import com.mitchell.claims.domain.Claim;
import java.util.List;

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
    public List<Claim> getAll() {
        return claimRepository.findAll();
    }

    @Override
    public Claim get(Long id) {
        return claimRepository.findById(id);
    }

    @Override
    public Claim create(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public Claim update(Claim claim) {
        return claimRepository.save(claim);
    }
}
