package com.ShoppingCart.dao;

import com.ShoppingCart.Model.User;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("loginDao")
public class LoginDaoImp extends AbstractDao<Integer, User> implements LoginDao {

    static final Logger logger = LoggerFactory.getLogger(LoginDaoImp.class);

    public User findById(int id) {
        User user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public User findByEmail(String emailId) {
        logger.info("emailId : {}", emailId);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("emailId", emailId));
        User user = (User) crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();

        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load.
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByEmail(String emailId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("emailId", emailId));
        User user = (User) crit.uniqueResult();
        delete(user);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }
}