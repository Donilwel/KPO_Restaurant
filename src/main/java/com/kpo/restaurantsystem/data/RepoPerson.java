package com.kpo.restaurantsystem.data;

import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.enumer.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoPerson extends JpaRepository<EnumPerson, Long> {
    Optional<EnumPerson> findByLogin(final String login);
    Boolean existsByLoginAndPasswordAndRole(final String login, final String password, final EnumRole role);
}
