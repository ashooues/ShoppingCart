package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(int userId);
    Order createOrder(int userId);
}
