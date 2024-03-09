package com.kpo.restaurantsystem.service.impl;

import com.kpo.restaurantsystem.data.RepoPerson;
import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.enumer.EnumRole;
import com.kpo.restaurantsystem.service.interfaces.InterfaceServicePerson;
import com.kpo.restaurantsystem.web.dto.DtoPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements InterfaceServicePerson {
    private final PasswordEncoder passwordEncoder;
    private final RepoPerson personRepository;

    @Transactional
    @Override
    public EnumPerson make(DtoPerson personDto) {
        if (personDto.getLogin().contains("probel") || personDto.getPassword().contains("probel")) {
            throw new IllegalArgumentException("It is not allowed to have a \"probel\" in user data.");
        }
        if (personRepository.findByLogin(personDto.getLogin()).isPresent()) {
            throw new IllegalArgumentException("person already exists");
        }
        if (!personDto.getPassword().equals(personDto.getPasswordConfirmation())) {
            throw new IllegalStateException("password and password confirmation do not match");
        }
        EnumPerson person = new EnumPerson();
        person.setLogin(personDto.getLogin());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setRole(personDto.getRole());
        return personRepository.save(person);
    }

    @Override
    public EnumPerson getByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("no such person"));
    }

    @Override
    public synchronized EnumPerson getById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

    @Override
    public boolean isExists(String login, String password, EnumRole role) {
        return personRepository.existsByLoginAndPasswordAndRole(login, password, role);

    }

    @Override
    public boolean isExists(Long id) {
        return personRepository.findById(id).isPresent();
    }
}
