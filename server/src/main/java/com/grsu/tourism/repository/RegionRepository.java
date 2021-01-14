package com.grsu.tourism.repository;

import com.grsu.tourism.model.Region;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RegionRepository extends PagingAndSortingRepository<Region, Integer> {
    List<Region> findByIdIn(Collection<Integer> regionIds);

}