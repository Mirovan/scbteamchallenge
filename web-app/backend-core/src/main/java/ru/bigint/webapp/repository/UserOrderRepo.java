package ru.bigint.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bigint.webapp.entity.UserOrderEntity;

import java.util.List;

public interface UserOrderRepo extends CrudRepository<UserOrderEntity, Integer> {
    List<UserOrderEntity> findAllByOrderByCreatedAtDesc();
}
