package com.grsu.tourism.repository;

import com.grsu.tourism.model.Location;
import com.grsu.tourism.model.user.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
    List<Comment> findByUserId(Integer userId);

    List<Comment> findByServiceId(Integer serviceId);

    List<Comment> findByServiceIdIn(Collection<Integer> serviceIds);

}