package com.grsu.tourism.repository;

import com.grsu.tourism.model.OpeningHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OpeningHoursRepository extends CrudRepository<OpeningHours, Integer> {
    List<OpeningHours> findByServiceIdIn(Collection<Integer> serviceIds);

}