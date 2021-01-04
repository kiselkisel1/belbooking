package com.grsu.tourism.repository;

import com.grsu.tourism.model.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends CrudRepository<Facility, Integer> {

}
