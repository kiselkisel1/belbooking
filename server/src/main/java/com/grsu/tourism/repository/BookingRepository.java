package com.grsu.tourism.repository;

import com.grsu.tourism.model.Booking;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer> {

}