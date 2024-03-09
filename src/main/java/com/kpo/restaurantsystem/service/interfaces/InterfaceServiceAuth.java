package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.web.dto.DtoAuthRequest;
import com.kpo.restaurantsystem.web.dto.DtoAuthResponse;

public interface InterfaceServiceAuth {
    DtoAuthResponse login(final DtoAuthRequest request);
}
