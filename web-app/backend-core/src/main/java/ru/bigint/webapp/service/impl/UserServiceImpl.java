package ru.bigint.webapp.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bigint.webapp.entity.Role;
import ru.bigint.webapp.dto.user.UserDto;
import ru.bigint.webapp.entity.UserEntity;
import ru.bigint.webapp.repository.UserRepo;
import ru.bigint.webapp.service.iface.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }


    @Override
    public UserEntity registerUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setFirstname(userDto.getFirstname());
        user.setSirname(userDto.getSirname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        //ToDo: рефакторить
        user.setRoles(List.of(new Role(1, "ROLE_USER", "")));

        return userRepo.save(user);
    }
}
