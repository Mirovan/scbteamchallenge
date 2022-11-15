package ru.bigint.webapp.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bigint.webapp.dto.UserOrder;
import ru.bigint.webapp.dto.chart.Candle;
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
    public List<UserOrder> getOrderBook() {
        return userOrderService.getOrders();
    }

}
