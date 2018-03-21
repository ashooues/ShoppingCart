package com.ShoppingCart.dao;

import com.ShoppingCart.Model.UserProfile;

import java.util.List;



public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);

}