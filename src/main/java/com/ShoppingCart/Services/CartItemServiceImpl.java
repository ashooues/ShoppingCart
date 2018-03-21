package com.ShoppingCart.Services;

import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.dao.CartDao;
import com.ShoppingCart.dao.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("cartServicesImpl")
@Transactional

public class CartItemServiceImpl implements CartItemServices {
    @Autowired
    CartItemDao dao;
    public CartItem addProductsIntoCart(int userId, int productId){ return dao.addProductsIntoCart(userId,productId);}
    public CartItem getCartItem(int userId,int productId){ return dao.getCartItem(userId,productId);}
    public CartItem updateQuantity(int userId,int productId,int quantity){ return dao.updateQuantity(userId,productId,quantity);}
    public CartItem removeFromCart(int userId,int productId){return dao.removeFromCart(userId,productId);}
}
