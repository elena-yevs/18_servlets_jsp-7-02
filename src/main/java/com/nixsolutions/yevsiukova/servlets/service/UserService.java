package com.nixsolutions.yevsiukova.servlets.service;

import com.nixsolutions.yevsiukova.servlets.dto.UserEntityDTO;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml.JdbcUserDao;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;

import java.util.List;

public class UserService {
    private final JdbcUserDao userDao;

    public UserService(JdbcUserDao userDao) {
        this.userDao = new JdbcUserDao();
    }

    public void create(UserEntityDTO userEntityDTO) {
        userDao.create(userEntityDTO.toEntity());
    }

    public void update(UserEntityDTO userEntityDTO) {
        userDao.update(userEntityDTO.toEntity());
    }

    public void remove(UserEntityDTO userEntityDTO) {
        userDao.remove(userEntityDTO.toEntity());
    }

    public UserEntity findById(Long id) {
        return userDao.findById(id);
    }

    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public UserEntity findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }


}
