package com.grsu.tourism.repository;

import com.grsu.tourism.model.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

    List<Menu> findByServiceIdIn(Collection<Integer> serviceIds);

}