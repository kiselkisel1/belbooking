package com.grsu.tourism.repository;

import com.grsu.tourism.model.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends PagingAndSortingRepository<Bookmark, Integer> {

    List<Bookmark> findByUserId(Integer userId);

    List<Bookmark> findByServiceId(Integer serviceId);

    void deleteByUserId(Integer userId);
}