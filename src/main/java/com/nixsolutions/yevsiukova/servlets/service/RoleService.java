package com.nixsolutions.yevsiukova.servlets.service;

import com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml.JdbcRoleDao;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;

import java.util.List;


public class RoleService {
    private final JdbcRoleDao roleDao;

    public RoleService(JdbcRoleDao roleDao) {
        this.roleDao = new JdbcRoleDao();
    }

    public void create(RoleEntity roleEntity) {
        roleDao.create(roleEntity);
    }

    public void update(RoleEntity roleEntity) {
        roleDao.update(roleEntity);
    }

    public void remove(RoleEntity roleEntity) {
        roleDao.remove(roleEntity);
    }

    public RoleEntity findByName(String name) {
        return roleDao.findByName(name);
    }

    public RoleEntity findById(Long id) {
        return roleDao.findById(id);
    }

    public List<RoleEntity> findAll() {
        return roleDao.findAll();
    }
}
