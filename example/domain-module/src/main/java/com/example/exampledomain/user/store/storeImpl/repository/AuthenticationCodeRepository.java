package com.example.exampledomain.user.store.storeImpl.repository;

import com.example.exampledomain.user.AuthenticationCode;
import com.example.exampledomain.user.sdo.AuthType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationCodeRepository extends JpaRepository<AuthenticationCode, String> {
    AuthenticationCode findByCodeAndAddressAndIsCheckedAndType(String code, String address, Boolean isChecked, AuthType type);
}
