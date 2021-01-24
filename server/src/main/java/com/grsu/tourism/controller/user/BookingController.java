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
    @PostMapping("/add")
    @ApiOperation(value = "From json should be removed id and paymentDate,userId,because it will be set by system", authorizations = {@Authorization(value = "jwtToken")})
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/changeStatus")
    @ApiOperation(value = "Change booking status: CANCELED,CLOSED,IN_PROGRESS", authorizations = {@Authorization(value = "jwtToken")})
    public Booking changeStatus(@RequestParam Status status, @RequestParam Integer bookingId) {
        return bookingService.changeStatus(status, bookingId);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/edit")
    @ApiOperation(value = "For editing booking only two fields can be changed: paymentCurrency and status", authorizations = {@Authorization(value = "jwtToken")})
    public Booking editBooking(@RequestBody Booking booking, @RequestParam Integer bookingId) {
        return bookingService.editBooking(bookingId, booking);
    }
}