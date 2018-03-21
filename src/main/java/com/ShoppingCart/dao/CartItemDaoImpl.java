/*
package com.ShoppingCart.dao;

import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl extends AbstractDao<Integer, Product> implements CartItemDao{


    private final List<CartItem> cartLines = new ArrayList<CartItem>();
    public CartItem findLineByCode(int code) {
        for (CartItem line : this.cartLines) {
            if (line.getProductInfo().getCode() == code) {
                return line;
            }
        }
        return null;
    }

    public void addProduct(Product productInfo, int quantity) {
        CartItem line = this.findLineByCode(productInfo.getProductId());

        if (line == null) {
            line = new CartItem();
            line.setQuantity(0);
            line.setProductInfo(productInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateProduct(int code, int quantity) {
        CartItem line = this.findLineByCode(code);

        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(Product productInfo) {
        CartItem line = this.findLineByCode(productInfo.getProductId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartItem line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartItem line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartItem> lines = cartForm.getCartLines();
            for (CartItem line : lines) {
                this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
            }
        }

    }
}
*/
package com.ShoppingCart.dao;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.CartItem;
import com.ShoppingCart.Model.Product;
import com.ShoppingCart.Model.User;
import com.ShoppingCart.dao.AbstractDao;
import com.ShoppingCart.dao.CartDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cartItemDao")
public class CartItemDaoImpl extends AbstractDao<Integer,CartItem> implements CartItemDao {

    @Autowired
    CartDaoImpl cartDao;
    @Autowired
    ProductDaoImpl productDao;
    @Autowired
    LoginDaoImp loginDao;


    public CartItem addProductsIntoCart(int userId, int productId) {


        Product product = productDao.findByPdId(productId);
        Cart cart = cartDao.getCart(userId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("cart", cart));
        crit.add(Restrictions.eq("product", product));
        List<CartItem> list = crit.list();
        CartItem cartItem = (CartItem) crit.uniqueResult();


        if (cartItem != null) {
            int q = cartItem.getQuantity();
            cartItem.setQuantity(q + 1);
            update(cartItem);
            return cartItem;
        } else {

            CartItem ci = new CartItem();
            ci.setCart(cartDao.getCart(userId));
            ci.setQuantity(1);
            ci.setProduct(productDao.findByPdId(productId));
            System.out.println(ci.getCart().getCartId());
            System.out.println(ci.getProduct().getProductId());
            persist(ci);
            return ci;
        }
    }
    public CartItem getCartItem(int userId, int productId) {
        Product product = productDao.findByPdId(productId);
        Cart cart = cartDao.getByKey(userId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("product", product));
        crit.add(Restrictions.eq("cart", cart));
        CartItem c = (CartItem) crit.uniqueResult();
        return c;
    }
    public CartItem updateQuantity(int userId, int productId, int quantity){
        Product product = productDao.findByPdId(productId);
        Cart cart = cartDao.getByKey(userId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("cart", cart));
        crit.add(Restrictions.eq("product", product));
        CartItem cartItem = (CartItem) crit.uniqueResult();
        cartItem.setQuantity(quantity);
        return cartItem;
    }
    public CartItem removeFromCart(int userId,int productId){
        Product product = productDao.findByPdId(productId);
        Cart cart = cartDao.getByKey(userId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("cart", cart));
        crit.add(Restrictions.eq("product", product));
        CartItem c = (CartItem) crit.uniqueResult();
        delete(c);
        return c;
    }



    /*
    @Autowired
    SessionFactory sessionFactory;


    public CartItem addProductsIntoCart(int userID, int productID){
        Cart cart=  new Cart();
        cart.setCartId(userID);

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(cart);

To make a seeion of anyother persistent class
//        Criteria criteria = getSession().createCriteria(Cart.class);



        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CartItem.class);
        criteria.add(Restrictions.eq("cartId", userID));
        criteria.add(Restrictions.eq("productId", productID));

        List<CartItem> list = criteria.list();
        if (list.size() > 0) {
            CartItem cartItem = list.get(0);
            int q = cartItem.getQuantity();
            cartItem.setQuantity(q + 1);
            session = sessionFactory.getCurrentSession();

            session.update(cartItem);


            return cartItem;
        }

        CartItem ci = new CartItem();
        ci.setCartId(userID);
        ci.setQuantity(1);
        ci.setProductId(productID);
        session = sessionFactory.getCurrentSession();
        session.persist(ci);

        criteria = sessionFactory.getCurrentSession().createCriteria(CartItem.class);
        criteria.add(Restrictions.eq("cartId", userID));
        criteria.add(Restrictions.eq("productId", productID));
        list = criteria.list();
        ci = list.get(0);
        return ci;

    }
*/

}
