package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Order;
import com.ShoppingCart.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDao dao;

    public List<Order> getOrders(int userId){ return dao.getOrders(userId);}
    public Order createOrder(int userId){ return dao.createOrder(userId);}
}
