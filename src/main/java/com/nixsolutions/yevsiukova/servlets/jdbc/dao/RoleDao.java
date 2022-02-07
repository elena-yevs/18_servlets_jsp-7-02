package com.nixsolutions.yevsiukova.servlets.jdbc.dao;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;

public interface RoleDao extends Dao<RoleEntity> {

    RoleEntity findByName(String name);

}
