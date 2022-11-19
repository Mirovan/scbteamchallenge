package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.user.UserDto;
import ru.bigint.webapp.entity.UserEntity;

public interface UserService {
    UserEntity registerUser(UserDto userDto);
}
