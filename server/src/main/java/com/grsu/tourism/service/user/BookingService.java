package com.grsu.tourism.service.user;

import com.grsu.tourism.model.Booking;
import com.grsu.tourism.model.Status;
import com.grsu.tourism.oauth.model.UserDto;
import com.grsu.tourism.oauth.service.UserService;
import com.grsu.tourism.repository.BookingRepository;
import com.grsu.tourism.validator.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ValidateUtil validateService;
    private final UserService userService;

    public Booking save(Booking booking) {
        validateService.isServiceExists(booking.getService_id());
        String email = userService.getCurrentUserEmail();
        UserDto userDto = Optional.ofNullable(userService.getByEmail(email))
                .orElseThrow(() -> new IllegalArgumentException("User with email is not found in the system " + email));

        booking.setUser_id(userDto.getId());
        booking.setPaymentDate(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public Booking changeStatus(Status status, Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking with id is not found " + bookingId));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }
}