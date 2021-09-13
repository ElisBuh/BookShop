package com.senla.service;

import com.senla.api.dao.IRoleDao;
import com.senla.api.dao.IUserDao;
import com.senla.api.service.IUserService;
import com.senla.model.Role;
import com.senla.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserDao userDao, IRoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user, Integer idRole) {
        log.info("user: {}, role: {}", user.getLogin(), idRole);
        user.setRole(roleDao.get(idRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User findByUserLogin(String login) {
        return userDao.findByUserLogin(login);
    }

    @Override
    public User findByUserLoginAndPassword(String login, String password) {
        log.info(login);
        User user = userDao.findByUserLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User changeRole(String login, Integer idRole) {
        User user = userDao.findByUserLogin(login);
        Role role = roleDao.get(idRole);
        user.setRole(role);
        userDao.update(user);
        return user;
    }

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }
}
