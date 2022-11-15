package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.UserOrder;
import ru.bigint.webapp.service.iface.UserOrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrdersServiceImpl implements UserOrderService {

    @Override
    public List<UserOrder> getOrders() {
        List<UserOrder> res = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            UserOrder userOrder = new UserOrder();
            userOrder.setId(1);
            userOrder.setDateTime(LocalDateTime.now());
            userOrder.setOperation("BUY");
            userOrder.setTiker("USDRUB_TOM");
            userOrder.setPrice(65.23d);
            userOrder.setLot(42);
            userOrder.setVolume(
                    BigDecimal.valueOf(userOrder.getPrice() * userOrder.getLot()).setScale(4, RoundingMode.FLOOR)
            );
            userOrder.setStatus("Исполнено");
            res.add(userOrder);
        }

        return res;
    }
}
