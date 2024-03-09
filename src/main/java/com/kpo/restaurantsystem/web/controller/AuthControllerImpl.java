package com.kpo.restaurantsystem.web.controller;

import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.enumer.EnumRole;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServiceAuth;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import com.kpo.restaurantsystem.web.controller.interfaces.InterfaceAuthController;
import com.kpo.restaurantsystem.web.dto.DtoAuthRequest;
import com.kpo.restaurantsystem.web.dto.DtoAuthResponse;
import com.kpo.restaurantsystem.web.dto.DtoPerson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authorization Controller",
        description = "Registration and authorization of admins and users"
)
public class AuthControllerImpl implements InterfaceAuthController {
    private final InterfaceServicePerson servicePerson;
    private final InterfaceServiceAuth serviceAuth;
    @PostMapping("/login")
    @Operation(summary = "Authorization")
    @Override
    public ResponseEntity<DtoAuthResponse> login(DtoAuthRequest dtoAuthRequest) {
        return ResponseEntity.ok(serviceAuth.login(dtoAuthRequest));
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Admin registration")
    @Override
    public ResponseEntity<EnumPerson> registerAdmin(@Valid @RequestBody DtoPerson dtoPerson) {
        dtoPerson.setRole(EnumRole.ROLE_ADMIN);
        return ResponseEntity.ok(servicePerson.make(dtoPerson));
    }

    @PostMapping("/register/user")
    @Operation(summary = "User registration")
    @Override
    public ResponseEntity<EnumPerson> registerUser(@Valid @RequestBody DtoPerson dtoPerson) {
        dtoPerson.setRole(EnumRole.ROLE_USER);
        return ResponseEntity.ok(servicePerson.make(dtoPerson));
    }
}
