package com.grsu.tourism.repository;

import com.grsu.tourism.model.Tourism;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TourismRepository extends PagingAndSortingRepository<Tourism, Integer> {

    List<Tourism> findByIdIn(Collection<Integer> serviceIds);

    List<Tourism> findByTypeAndIsBooked(String type, Pageable pageable, boolean isBooked);

    List<Tourism> findByTypeAndSubTypeAndIsBooked(String type, String subType, Pageable pageable, boolean isBooked);
}
