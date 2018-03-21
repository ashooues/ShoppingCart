package com.ShoppingCart.Services;

import com.ShoppingCart.Model.Cart;
import com.ShoppingCart.Model.User;
import com.ShoppingCart.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("CartServices")
@Transactional

public class CartServicesImpl implements CartServices{
    @Autowired
    CartDao dao;

    public Cart getCart(int id){return dao.getCart(id);}

    public void makeCart(User user){
        dao.makeCartForUser(user);
    }
}
