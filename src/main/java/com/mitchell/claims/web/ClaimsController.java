package com.mitchell.claims.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.mitchell.claims.domain.Claim;
import com.mitchell.claims.service.ClaimService;
import com.mitchell.claims.web.dto.ClaimsList;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */

@RestController
@RequestMapping("/claims")
public class ClaimsController {

    @Autowired
    private ClaimService claimService;

//    @RequestMapping(method = RequestMethod.GET)
//    public ClaimsList getAll() {
//        return new ClaimsList(claimService.getAll());
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ClaimsList search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date lossDateFrom, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date lossDateTo) {
        return new ClaimsList(claimService.search(lossDateFrom, lossDateTo == null ? new Date() : lossDateTo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Claim get(@PathVariable Long id) {
        return claimService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Claim create(@RequestBody Claim claim) {
        return claimService.create(claim);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Claim update(@PathVariable Long id, @RequestBody Claim claim) {
        return claimService.update(claim);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        claimService.delete(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        for (long i = 0; i < 5; i++) {
            claimService.create(new Claim(i, "Claim " + i));
        }
        return "DONE!";
    }
}
