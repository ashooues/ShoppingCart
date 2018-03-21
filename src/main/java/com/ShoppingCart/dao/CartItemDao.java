
package com.ShoppingCart.dao;
import com.ShoppingCart.Model.CartItem;

public interface CartItemDao {
    CartItem addProductsIntoCart(int userId, int productId);
    CartItem getCartItem(int userId,int productId);
    CartItem updateQuantity(int userId,int productId,int quantity);
    CartItem removeFromCart(int userId,int productId);

}



