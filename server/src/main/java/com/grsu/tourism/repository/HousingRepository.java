package com.grsu.tourism.repository;

import com.grsu.tourism.model.Housing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HousingRepository extends PagingAndSortingRepository<Housing, Integer> {

    List<Housing> findByIdIn(Collection<Integer> serviceIds);

    List<Housing> findByTypeAndIsBooked(String type, Pageable pageable, boolean isBooked);

    List<Housing> findByTypeAndSubTypeAndIsBooked(String type, String subType, Pageable pageable, boolean isBooked);

}