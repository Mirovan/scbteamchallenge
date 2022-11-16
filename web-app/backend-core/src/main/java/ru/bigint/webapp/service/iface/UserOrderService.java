package ru.bigint.webapp.service.iface;

import ru.bigint.webapp.dto.UserOrder;

import java.util.List;

public interface UserOrderService {
    /**
     * Получить все заявки пользователя
     */
    List<UserOrder> getAll();

    /**
     * Добавление заявки от пользователя
     * */
    UserOrder add(UserOrder userOrder);

    /**
     * Обновление заявки от пользователя
     * */
    UserOrder update(UserOrder userOrder);
}
