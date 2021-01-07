package com.grsu.tourism.controller;

import com.grsu.tourism.model.ContactDetails;
import com.grsu.tourism.service.impl.ContactDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contactDetails")
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public ContactDetails addContactDetails(@RequestBody ContactDetails contactDetails) {
        return contactDetailsService.save(contactDetails);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void deleteContactDetails(@RequestParam Integer id) {
        contactDetailsService.delete(id);
    }
}