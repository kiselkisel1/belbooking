package com.grsu.tourism.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grsu.tourism.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "booking")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer serviceId;
    private Integer userId;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime bookingDate;
    private String paymentCurrency;

}