package com.ShoppingCart.Services;

import com.ShoppingCart.Model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findByEmail(String emailId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByEmail(String emailId);

    List<User> findAllUsers();


}