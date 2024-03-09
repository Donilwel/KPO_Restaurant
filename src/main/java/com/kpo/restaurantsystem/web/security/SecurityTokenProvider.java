package com.kpo.restaurantsystem.web.security;

import com.kpo.restaurantsystem.enumer.EnumRole;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityTokenProvider {
    private final UserDetailsService userDetailsService;
    private final InterfaceServicePerson personService;

    public String createPersonToken(final String login, final String password, final EnumRole role) {
        return login + "probel" + password + "probel" + role;
    }

    public boolean isValidToken(final String token) {
        String[] args = token.split("probel");
        return args.length == 3 && personService.isExists(args[0], args[1], EnumRole.valueOf(args[2]));
    }

    public Authentication getAuthentication(final String token) {
        String login = token.split("probel")[0];
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
