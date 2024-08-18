package com.example.domain.user.store.storeImpl.repository;

import com.example.domain.user.AuthenticationCode;
import com.example.domain.user.sdo.AuthType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationCodeRepository extends JpaRepository<AuthenticationCode, String> {
    AuthenticationCode findByCodeAndAddressAndIsCheckedAndType(String code, String address, Boolean isChecked, AuthType type);
}
