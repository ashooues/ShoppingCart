package com.ShoppingCart.dao;


import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.User;
import org.springframework.stereotype.Repository;


@Repository("CartDao")
public class CartDaoImpl extends AbstractDao<Integer,Cart> implements CartDao {



    public Cart getCart(int userId) {
        Cart c = getByKey(userId);
        return c;

    }

    public void makeCartForUser(User user){
        int userId = user.getUserId();

        Cart cart = new Cart();
        cart.setCartId(userId);
        cart.setUserId(userId);
        persist(cart);

    }
}











 /*   public CartItem addProductsIntoCart(int userId, int productId){

        Criteria crit= createEntityCriteria();
        crit.add(Restrictions.eq("cartId", userId));
        crit.add(Restrictions.eq("productId", productId));

        CartItem cartItem = (CartItem) crit.uniqueResult();
        Cart c = (Cart) crit.uniqueResult();


        if(cartItem!=null){
            int q = cartItem.getQuantity();
            cartItem.setQuantity(q + 1);
            update(c);
            return cartItem;
        }else {

            CartItem ci = new CartItem();
            Cart c1 = new Cart();
            ci.setCartId(userId);
            ci.setQuantity(1);
            c1.setcartId(productId);
            persist(c1);
        }
            return (CartItem) crit.uniqueResult();
        }
*/

