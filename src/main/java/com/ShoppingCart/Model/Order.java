package com.ShoppingCart.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="Orders")
public class Order {
    @Id
    @Column(name = "orderId", length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
//    @OneToOne(targetEntity = OrderItem.class, fetch = FetchType.EAGER)
    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    private List<OrderItem> orderItems;
    @Column(name = "STATUS")
    private String orderStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    User user;

    private int userId;


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
