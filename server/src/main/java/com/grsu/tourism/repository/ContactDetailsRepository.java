package com.grsu.tourism.repository;

import com.grsu.tourism.model.ContactDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContactDetailsRepository extends CrudRepository<ContactDetails, Integer> {
    List<ContactDetails> findByServiceIdIn(Collection<Integer> serviceIds);

}