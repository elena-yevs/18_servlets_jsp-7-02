package com.nixsolutions.yevsiukova.servlets.jdbc.dao;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;

import java.util.List;

public interface UserDao extends Dao<UserEntity> {

    void create(UserEntity userEntity);

    List<UserEntity> findAll();

    UserEntity findByLogin(String login);

    UserEntity findByEmail(String email);

}
