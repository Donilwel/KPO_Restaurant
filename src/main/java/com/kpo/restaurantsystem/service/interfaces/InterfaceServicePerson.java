package com.kpo.restaurantsystem.service.interfaces;

import com.kpo.restaurantsystem.enumer.EnumPerson;
import com.kpo.restaurantsystem.enumer.EnumRole;
import com.kpo.restaurantsystem.web.dto.DtoPerson;

public interface InterfaceServicePerson {
    EnumPerson make(final DtoPerson personDto);
    EnumPerson getByLogin(final String login);
    EnumPerson getById(final Long id);

    boolean isExists(final String login, final String password, final EnumRole role);
    boolean isExists(final Long id);
}
