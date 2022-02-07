package com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

@DBUnit(url = "jdbc:h2:mem:test-db;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "elena",
        password = "12345", schema = "PUBLIC")
public class JdbcRoleDaoTest {
    private static JdbcRoleDao roleDao;
    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();

    @BeforeClass
    public static void beforeClass() {
        roleDao = new JdbcRoleDao();
    }

    @Test
    @DataSet(value = "init-role-files/init-role-for-create.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-create.yml")
    public void create() {
        RoleEntity roleEntity = new RoleEntity(2L, "user");
        roleDao.create(roleEntity);
    }

    @Test
    @DataSet(value = "init-role-files/init-role-table.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-update.yml")
    public void update() {
        RoleEntity roleEntity = new RoleEntity(1L, "unknown");
        roleDao.update(roleEntity);
    }

    @Test
    @DataSet(value = "init-role-files/init-role-table.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-remove.yml")
    public void remove() {
        RoleEntity roleEntity = new RoleEntity(1L, "admin");
        roleDao.remove(roleEntity);
    }

    @Test
    @DataSet(value = "init-role-files/init-role-table.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-findByName.yml")
    public void findByName() {
        String roleName = "admin";
        roleDao.findByName(roleName);
    }

    @Test
    @DataSet(value = "init-role-files/init-role-for-findAll.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-findAll.yml")
    public void findAll() {
        roleDao.findAll();
    }

    @Test
    @DataSet(value = "init-role-files/init-role-table.yml", executeScriptsBefore = "sql/create-roles-table.sql",
            executeScriptsAfter = "sql/drop-roles-table.sql")
    @ExpectedDataSet(value = "role-expected-files/expect-role-findById.yml")
    public void findById() {
        Long roleId = 1L;
        roleDao.findById(roleId);
    }
}