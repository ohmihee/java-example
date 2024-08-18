package com.example.domain.user.store.storeImpl.repository;

import com.example.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, String> {
    Iterable<User> findAll(Sort sort);
    Page<User> findAll(Pageable pageable);
}
