package com.grsu.tourism.repository;

import com.grsu.tourism.model.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {
    List<Picture> findByServiceIdIn(Collection<Integer> serviceIds);

}