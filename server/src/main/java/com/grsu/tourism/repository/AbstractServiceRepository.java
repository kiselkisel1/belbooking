package com.grsu.tourism.repository;

import com.grsu.tourism.model.AbstractService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbstractServiceRepository extends PagingAndSortingRepository<AbstractService, Integer> {

    List<AbstractService> findByTypeAndIsBooked(String type, Pageable pageable, boolean isBooked);

    List<AbstractService> findByTypeAndSubTypeAndIsBooked(String type, String subType, Pageable pageable, boolean isBooked);

}