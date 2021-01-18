package com.grsu.tourism.controller.user;

import com.grsu.tourism.model.Booking;
import com.grsu.tourism.model.Status;
import com.grsu.tourism.service.user.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/bookService")
    @ApiOperation(value = "From json should be removed id and paymentDate,userId,because it will be set by system", authorizations = {@Authorization(value = "jwtToken")})
    public Booking addService(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/changeStatus")
    @ApiOperation(value = "Change booking status: CANCELED,CLOSED,IN_PROGRESS,", authorizations = {@Authorization(value = "jwtToken")})
    public Booking changeStatus(@RequestParam Status status, @RequestParam Integer bookingId) {
        return bookingService.changeStatus(status, bookingId);
    }
}