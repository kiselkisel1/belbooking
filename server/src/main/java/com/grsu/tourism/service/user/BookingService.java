package com.grsu.tourism.service.user;

import com.grsu.tourism.enums.ServiceType;
import com.grsu.tourism.factory.ServiceFactory;
import com.grsu.tourism.model.Status;
import com.grsu.tourism.model.user.Booking;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import com.grsu.tourism.repository.BookingRepository;
import com.grsu.tourism.service.GenericService;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ValidateUtil validateService;
    private final UserService userService;
    private final ServiceFactory serviceFactory;

    public Booking getById(Integer bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking with id is not found " + bookingId));
    }

    public List<Booking> getAll(Pageable pageable) {
        return bookingRepository.findAll(pageable).getContent();
    }

    public List<Booking> getBookingsForCurrentUser(Pageable pageable) {
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));
        return bookingRepository.findByUserId(userDto.getId(), pageable);
    }

    public Booking save(Booking booking) {
        validateService.isServiceExists(booking.getServiceId());
        ServiceType serviceType = ServiceType.getByNameIgnoreCaseOrElseThrow(booking.getServiceType());
        GenericService genericService = serviceFactory.getServiceByType(serviceType);
        genericService.setIsBooked(booking.getServiceId());

        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        booking.setUserId(userDto.getId());
        booking.setBookingDate(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public Booking changeStatus(Status status, Integer bookingId) {
        Booking booking = getById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    public Booking editBooking(Integer bookingId, Booking booking) {
        Booking oldBooking = getById(bookingId);
        booking.setId(bookingId);
        booking.setUserId(oldBooking.getUserId());
        booking.setServiceId(oldBooking.getServiceId());
        booking.setBookingDate(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }
}