package com.kpo.restaurantsystem.web.security;

import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.enumer.EnumRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class SecurityDetailsFactory {
    public SecurityDetails create(final EnumPerson person) {
        return new SecurityDetails(
                person.getLogin(),
                person.getPassword(),
                mapToGrantedAuthorities(person.getRole())
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(EnumRole role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
