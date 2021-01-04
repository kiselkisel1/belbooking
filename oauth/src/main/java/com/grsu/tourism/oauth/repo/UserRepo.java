package com.grsu.tourism.oauth.repo;

import com.grsu.tourism.oauth.model.UserDto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends PagingAndSortingRepository<UserDto, Integer> {
    UserDto findByEmail(String email);
}