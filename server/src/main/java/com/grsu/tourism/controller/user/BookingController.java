package com.grsu.tourism.controller.user;

import com.grsu.tourism.model.Status;
import com.grsu.tourism.model.user.Booking;
import com.grsu.tourism.service.user.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getById")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Booking getBooking(@RequestParam Integer id) {
        return bookingService.getById(id);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Booking> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "bookingDate") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return bookingService.getAll(paging);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/getBookingsForCurrentUser")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<Booking> getBookingsForCurrentUser(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "bookingDate") String sortBy) {

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return bookingService.getBookingsForCurrentUser(paging);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/add")
    @ApiOperation(value = "From json should be removed id and paymentDate,userId,because it will be set by system", authorizations = {@Authorization(value = "jwtToken")})
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/changeStatus")
    @ApiOperation(value = "Change booking status: CANCELED,CLOSED,IN_PROGRESS", authorizations = {@Authorization(value = "jwtToken")})
    public Booking changeStatus(@RequestParam Status status, @RequestParam Integer bookingId) {
        return bookingService.changeStatus(status, bookingId);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/edit")
    @ApiOperation(value = "For editing booking only two fields can be changed: paymentCurrency and status", authorizations = {@Authorization(value = "jwtToken")})
    public Booking editBooking(@RequestBody Booking booking, @RequestParam Integer bookingId) {
        return bookingService.editBooking(bookingId, booking);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete")
    @ApiOperation(value = "delete", authorizations = {@Authorization(value = "jwtToken")})
    public void delete(@RequestParam Integer id) {
        bookingService.delete(id);
    }
}