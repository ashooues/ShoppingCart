package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.Model.User;

public interface LoginDao {

    User findById(int id);
    void save(User user);
    List<User> findAllUsers();

    User findByEmail(String emailId);

    void deleteByEmail(String email);
    void updateUser(User user);

}

