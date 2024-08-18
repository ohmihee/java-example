package com.example.domain.user.store.storeImpl;

import com.example.domain.user.User;
import com.example.domain.user.sdo.UserCdo;
import com.example.domain.user.store.UserStore;
import com.example.domain.user.store.storeImpl.repository.UserPagingAndSortingRepository;
import com.example.domain.user.store.storeImpl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;
    private final UserPagingAndSortingRepository userPagingAndSortingRepository;
    @Override
    public boolean saveUser(UserCdo userCdo) {
        User user = new User(userCdo);
        return Optional.ofNullable(userRepository.save(user)).isPresent();
    }

    @Override
    public List<User> findAll() {
        return Optional.ofNullable(userRepository.findAll()).orElse(Collections.emptyList());
    }

    @Override
    public User findById(String id) {
        return Optional.ofNullable(userRepository.findById(id)).get().orElse(null);
    }

    @Override
    public List<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).orElse(Collections.emptyList());
    }

    @Override
    public boolean modifyUser(User dbUser) {
        return Optional.ofNullable(userRepository.save(dbUser)).isPresent();
    }

    @Override
    public List<User> findByPhone(String phone) {
        return Optional.ofNullable(userRepository.findByPhoneNumber(phone)).orElse(Collections.emptyList());
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userPagingAndSortingRepository.findAll(pageable);
    }


}
