package com.mitchell.claims.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mitchell.claims.model.Claim;

/**
 * @author Khaled Ayoubi
 */

@RestController
@RequestMapping("/claims")
public class ClaimsController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Claim get(@PathVariable String id) {
        return new Claim(id, "khaled");
    }
}
