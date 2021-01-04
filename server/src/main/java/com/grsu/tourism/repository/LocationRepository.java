package com.grsu.tourism.repository;

import com.grsu.tourism.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    List<Location> findByIdIn(Collection<Integer> serviceIds);

}