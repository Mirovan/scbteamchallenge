package ru.bigint.webapp.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bigint.webapp.dto.terminal.OrderBook;
import ru.bigint.webapp.service.iface.OrderBookService;

@Api(tags = {"Стакан"})
@Tag(name = "Стакан", description = "Сервис для работы со стаканом и заявками от участников Фондового Рынка")
@RestController
@RequestMapping(value = "/api/orderbook")
public class OrderBookRestController {

    private final OrderBookService orderBookService;

    public OrderBookRestController(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    @GetMapping
    public OrderBook getOrderBook(@RequestParam(name = "tiker") String tiker) {
        return orderBookService.getOrderBook(tiker);
    }

}
