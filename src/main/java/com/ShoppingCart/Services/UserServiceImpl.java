package com.ShoppingCart.Services;
import java.util.List;

import com.ShoppingCart.Model.User;
import com.ShoppingCart.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private LoginDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return dao.findById(id);
    }


    public User findByEmail(String emailId) {
        User user = dao.findByEmail(emailId);
        return user;
    }


    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
//        User entity = dao.findById(user.getUserId());
//        if(entity!=null){
//            entity.setEmailId(user.getEmailId());
//            if(!user.getPassword().equals(entity.getPassword())){
//                entity.setPassword(passwordEncoder.encode(user.getPassword()));
//            }
//            entity.setUserName(user.getUserName());
//            entity.setPhoneNo(user.getPhoneNo());
//            entity.setAddress(user.getAddress());
//
// //           entity.setEmailId(user.getEmailId());
//            entity.setUserProfiles(user.getUserProfiles());
//            System.out.println(entity.getAddress().getCity());

        dao.updateUser(user);
        }



    public void deleteUserByEmail(String emailId) {
        dao.deleteByEmail(emailId);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }


}