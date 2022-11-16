package ru.bigint.webapp.service.impl;

import org.springframework.stereotype.Service;
import ru.bigint.webapp.dto.UserOrder;
import ru.bigint.webapp.entity.UserOrderEntity;
import ru.bigint.webapp.repository.UserOrderRepo;
import ru.bigint.webapp.service.iface.UserOrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrdersServiceImpl implements UserOrderService {

    private final UserOrderRepo userOrderRepo;

    public UserOrdersServiceImpl(UserOrderRepo userOrderRepo) {
        this.userOrderRepo = userOrderRepo;
    }

    @Override
    public List<UserOrder> getAll() {
        List<UserOrderEntity> userOrderEntities =
                new ArrayList<>(userOrderRepo.findAllByOrderByCreatedAtDesc());

        return userOrderEntities.stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public UserOrder add(UserOrder userOrder) {
        userOrder.setStatus("WAITING");
        userOrder.setCreatedAt(LocalDateTime.now());

        return update(userOrder);
    }

    @Override
    public UserOrder update(UserOrder userOrder) {
        UserOrderEntity userOrderEntity = userOrderRepo.save(dtoToEntity(userOrder));
        return entityToDto(userOrderEntity);
    }

    private UserOrder entityToDto(UserOrderEntity entity) {
        UserOrder dto = new UserOrder();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setTiker(entity.getTiker());
        dto.setOperation(entity.getOperation());
        dto.setPrice(entity.getPrice());
        dto.setLot(entity.getLot());
        dto.setVolume(BigDecimal.valueOf(dto.getLot() * dto.getPrice()).setScale(4, RoundingMode.FLOOR));
        dto.setStatus(entity.getStatus());
        return dto;
    }

    private UserOrderEntity dtoToEntity(UserOrder dto) {
        UserOrderEntity entity = new UserOrderEntity();
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setTiker(dto.getTiker());
        entity.setOperation(dto.getOperation());
        entity.setPrice(dto.getPrice());
        entity.setLot(dto.getLot());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
