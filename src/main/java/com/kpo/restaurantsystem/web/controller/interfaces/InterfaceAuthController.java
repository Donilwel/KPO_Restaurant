package com.kpo.restaurantsystem.web.controller.interfaces;

import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.web.dto.DtoAuthRequest;
import com.kpo.restaurantsystem.web.dto.DtoAuthResponse;
import com.kpo.restaurantsystem.web.dto.DtoPerson;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface
InterfaceAuthController {
    ResponseEntity<EnumPerson> registerUser(@Valid @RequestBody DtoPerson dtoPerson);

    ResponseEntity<EnumPerson> registerAdmin(@Valid @RequestBody DtoPerson dtoPerson);

    ResponseEntity<DtoAuthResponse> login(DtoAuthRequest dtoAuthRequest);
}
