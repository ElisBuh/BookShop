package com.senla.dao;

import com.senla.api.dao.IUserDao;
import com.senla.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {


    @Override
    public User findByUserLogin(String login) {
        log.info("findByUserLogin {}", login);
        Session session = sessionFactory.openSession();
        return (User) session.getNamedQuery("findByUserLogin")
                .setParameter("login",login)
                .getSingleResult();
    }


    @Override
    protected Class<User> aClass() {
        return User.class;
    }
}
