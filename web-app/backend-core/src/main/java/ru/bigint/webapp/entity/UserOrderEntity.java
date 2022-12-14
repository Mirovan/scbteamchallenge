package ru.bigint.webapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_orders")
public class UserOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "tiker")
    private String tiker;

    @Column(name = "operation")
    private String operation;

    @Column(name = "price")
    private Double price;

    @Column(name = "lot")
    private Integer lot;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "userOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<UserDealEntity> userDeals;

    public UserOrderEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTiker() {
        return tiker;
    }

    public void setTiker(String tiker) {
        this.tiker = tiker;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserDealEntity> getUserDeals() {
        return userDeals;
    }

    public void setUserDeals(List<UserDealEntity> userDeals) {
        this.userDeals = userDeals;
    }
}
