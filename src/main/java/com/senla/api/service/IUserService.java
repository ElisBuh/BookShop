package com.senla.api.service;

import com.senla.model.User;

public interface IUserService {

    User saveUser(User user, Integer idRole);
    User findByUserLogin(String login);
    User findByUserLoginAndPassword(String login, String password);
    User changeRole (String login, Integer idRole);
    User get (Integer id);
}
