package com.grsu.tourism.repository;

import com.grsu.tourism.model.Transport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransportRepository extends PagingAndSortingRepository<Transport, Integer> {

    List<Transport> findByIdIn(Collection<Integer> serviceIds);

    List<Transport> findByType(String type, Pageable pageable);

    List<Transport> findByTypeAndSubType(String type, String subType, Pageable pageable);

}
