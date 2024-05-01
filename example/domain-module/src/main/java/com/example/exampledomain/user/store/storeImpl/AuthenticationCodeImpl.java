package com.example.exampledomain.user.store.storeImpl;

import com.example.exampledomain.user.AuthenticationCode;
import com.example.exampledomain.user.sdo.AuthType;
import com.example.exampledomain.user.sdo.AuthenticationCodeCdo;
import com.example.exampledomain.user.store.AuthenticationCodeStore;
import com.example.exampledomain.user.store.storeImpl.repository.AuthenticationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationCodeImpl implements AuthenticationCodeStore {
    private final AuthenticationCodeRepository authenticationCodeRepository;
    @Override
    public boolean saveCode(AuthenticationCode authenticationCode) {
        return Optional.ofNullable(authenticationCodeRepository.save(authenticationCode)).isPresent();
    }
    @Override
    public boolean saveCode(AuthenticationCodeCdo authenticationCodeCdo) {
        AuthenticationCode authenticationCode = new AuthenticationCode(authenticationCodeCdo);
        return Optional.ofNullable(authenticationCodeRepository.save(authenticationCode)).isPresent();
    }

    @Override
    public AuthenticationCode checkAuthentication(String code, String address, AuthType type) {
        return authenticationCodeRepository.findByCodeAndAddressAndIsCheckedAndType(code, address, false, type);
    }
}
