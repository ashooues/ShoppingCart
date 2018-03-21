package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Order;
import com.ShoppingCart.Model.OrderItem;
import com.ShoppingCart.Services.CartItemServices;
import com.ShoppingCart.Services.CartServices;
import com.ShoppingCart.Services.ProductServices;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer,Order> implements OrderDao{
@Autowired
CartServices cartServices;

@Autowired
ProductServices productServices;

@Autowired
OrderItemDao orderItemDao;

@Autowired
    CartItemServices cartItemServices;

    public List<Order> getOrders(int userId){
        int cartId =cartServices.getCart(userId).getCartId();
        Criteria crit= createEntityCriteria();
        crit.add(Restrictions.eq("userId", cartId));
//        Order order = (Order) crit.uniqueResult();
        return (List<Order>)crit.list();
    }

    public Order createOrder(int userId){

        int cartId =cartServices.getCart(userId).getCartId();
        Criteria crit= createEntityCriteria();

        crit.add(Restrictions.eq("userId",cartId));

        Order order = new Order();

        List<OrderItem> orderItems = new LinkedList<OrderItem>();
        List<CartItem> cartItems = (List<CartItem>) cartServices.getCart(cartId).getCartItems();


        for( CartItem cartItem: cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            int productId = cartItem.getProduct().getProductId();
            cartItemServices.removeFromCart(userId,productId);

        }


        order.setOrderItems(orderItems);
//        order.setOrderStatus("Order Confirmed");

        order.setUserId(userId);

        persist(order);

        return order;
    }
}

/*//            orderItem.setOrderItemId(cartItem.getCartItemId());
            orderItem.setOrder(order);
            orderItemDao.saveOrderItem(orderItem);
            orderItems.add(orderItem);
//            cartItemServices.removeFromCart(cartItem.getCartItemId(),userId);*/