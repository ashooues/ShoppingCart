package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import com.ShoppingCart.Model.User;

import java.util.List;

public interface CartDao {
    Cart getCart(int id);

    void makeCartForUser(User user);
}
