/*
package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import com.ShoppingCart.dao.CartItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("CartServices")
@Transactional

public class CartItemServices {
    @Autowired
    CartItemDao dao;

    private CartItem findLineByCode(int code){ return dao.findLineByCode(code)}
    public void addProduct(Product productInfo, int quantity){ return dao.addProduct(productInfo,quantity)}
    public void updateProduct(int code, int quantity){ return dao.updateProduct(code, quantity);}
    public void removeProduct(Product productInfo){ return dao.removeProduct(productInfo);}
    public void updateQuantity(Cart cartForm){ return dao.updateQuantity(cartForm);}
}
*/
package com.ShoppingCart.Services;

import com.ShoppingCart.Model.CartItem;

public interface CartItemServices {
    CartItem addProductsIntoCart(int userId, int productId);
    CartItem updateQuantity(int userId,int productId,int quantity);
    CartItem getCartItem(int userId,int productId);
    CartItem removeFromCart(int userId,int productId);
}
