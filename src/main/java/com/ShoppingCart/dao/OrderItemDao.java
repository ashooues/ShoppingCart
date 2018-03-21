package com.ShoppingCart.dao;

import com.ShoppingCart.Model.OrderItem;

public interface OrderItemDao {
    void saveOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(int id);
}
