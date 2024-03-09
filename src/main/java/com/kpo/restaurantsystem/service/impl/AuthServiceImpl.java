package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.web.security.SecurityTokenProvider;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceAuth;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import com.kpo.restaurantsystem.web.dto.DtoAuthRequest;
import com.kpo.restaurantsystem.web.dto.DtoAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements InterfaceServiceAuth {
    private final AuthenticationManager authenticationManager;
    private final InterfaceServicePerson interfaceServicePerson;
    private final SecurityTokenProvider securityTokenProvider;

    @Override
    public DtoAuthResponse login(DtoAuthRequest dtoAuthRequest) {
        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoAuthRequest.getLogin(), dtoAuthRequest.getPassword()));
        if (authenticate == null) {
            throw new IllegalStateException("error");
        }
        System.out.println(authenticate.isAuthenticated());
        var process = interfaceServicePerson.getByLogin(dtoAuthRequest.getLogin());
        DtoAuthResponse dtoAuthResponse = new DtoAuthResponse();
        dtoAuthResponse.setPersonToken(securityTokenProvider.createPersonToken(process.getLogin(), process.getPassword(), process.getRole()));
        return dtoAuthResponse;
    }
}
