package com.senla.api.dao;

import com.senla.model.User;

public interface IUserDao extends GenericDao<User>{

    User findByUserLogin(String login);
}
