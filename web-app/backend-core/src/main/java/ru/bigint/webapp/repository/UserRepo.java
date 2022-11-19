package ru.bigint.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bigint.webapp.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
