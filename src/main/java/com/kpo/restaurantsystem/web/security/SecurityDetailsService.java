package com.kpo.restaurantsystem.web.security;

import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityDetailsService implements UserDetailsService {
    private final SecurityDetailsFactory personDetailsFactory;
    private final InterfaceServicePerson personService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = personService.getByLogin(username);
        return personDetailsFactory.create(person);
    }
}
