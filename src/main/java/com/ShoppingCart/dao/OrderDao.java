package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Order;
import com.ShoppingCart.Model.OrderItem;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders(int userId);
    Order createOrder(int userId);
}
