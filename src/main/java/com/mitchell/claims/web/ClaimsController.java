package com.mitchell.claims.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.service.ClaimService;

/**
 * @author Khaled Ayoubi
 */

@RestController
@RequestMapping("/claims")
public class ClaimsController {

    @Autowired
    private ClaimService claimService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Claim get(@PathVariable Long id) {
        return claimService.get(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        for (long i = 0; i < 5; i++) {
            claimService.create(new Claim(i, "Claim " + i));
        }
        return "DONE!";
    }
}
