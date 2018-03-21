package com.ShoppingCart.dao;

import com.ShoppingCart.Model.OrderItem;
import org.springframework.stereotype.Repository;

@Repository("orderItemDao")
public class orderItemDaoImpl extends AbstractDao<Integer,OrderItem> implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem){
        persist(orderItem);
    }
    @Override
    public OrderItem getOrderItem(int id){
        return getByKey(id);
    }

}
