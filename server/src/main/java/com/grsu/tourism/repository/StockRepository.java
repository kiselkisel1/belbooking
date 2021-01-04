package com.grsu.tourism.repository;

import com.grsu.tourism.model.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, Integer> {

    List<Stock> findByServiceIdIn(Collection<Integer> serviceIds);

}
