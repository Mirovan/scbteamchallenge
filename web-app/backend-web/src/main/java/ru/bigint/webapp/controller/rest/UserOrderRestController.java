package ru.bigint.webapp.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bigint.webapp.dto.terminal.UserOrder;
import ru.bigint.webapp.service.iface.UserOrderService;

import java.util.List;

@Api(tags = {"Заявки пользователя"})
@Tag(name = "Заявки пользователя", description = "Сервис для работы с заявками пользователя")
@RestController
@RequestMapping(value = "/api/user-orders")
public class UserOrderRestController {

    private final UserOrderService userOrderService;

    public UserOrderRestController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @GetMapping
    public List<UserOrder> getOrders() {
        return userOrderService.getAll();
    }

    @PostMapping("/save")
    public UserOrder saveOrder(@RequestBody UserOrder userOrder) {
        return userOrderService.add(userOrder);
    }

}
