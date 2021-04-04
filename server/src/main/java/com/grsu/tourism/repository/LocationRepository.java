package com.grsu.tourism.repository;

import com.grsu.tourism.model.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {
    List<Location> findByServiceIdIn(Collection<Integer> serviceIds);

}