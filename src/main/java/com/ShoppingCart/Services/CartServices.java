package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import com.ShoppingCart.Model.User;

import java.util.List;

public interface CartServices {
    Cart getCart(int id);

    void makeCart(User user);
}
