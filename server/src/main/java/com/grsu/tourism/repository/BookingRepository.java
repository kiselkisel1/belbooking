package com.grsu.tourism.repository;

import com.grsu.tourism.model.user.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {

    List<Booking> findByUserId(Integer userId, Pageable pageable);

}