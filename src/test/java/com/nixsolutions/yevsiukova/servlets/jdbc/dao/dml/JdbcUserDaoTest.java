package com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import org.junit.Rule;
import org.junit.Test;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


@DBUnit(url = "jdbc:h2:mem:test-db;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "elena",
        password = "12345", schema = "PUBLIC")
public class JdbcUserDaoTest {

    JdbcUserDao userDao = new JdbcUserDao();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();


    @Test
    @DataSet(value = "init-user-files/init-users-table-for-create.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "user-expected-files/expect-user-create.yml", ignoreCols = "id")
    public void create() {
        UserEntity userEntity = new UserEntity(2L, 1L, "my-login", "qwerty7",
                "mark@gmail.com", "Mark", "Sheppard",
                new Date(new GregorianCalendar(2022, Calendar.JANUARY, 26).getTimeInMillis()));
        userDao.create(userEntity);

    }

    @Test
    @DataSet(value = "init-user-files/init-users-table-for-update.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "user-expected-files/expect-user-update.yml")
    public void update() {
        UserEntity userEntity = new UserEntity(1L, 1L, "my-login", "qwerty7",
                "mark@gmail.com", "Mark", "Sheppard",
                new Date(new GregorianCalendar(2022, Calendar.JANUARY, 26).getTimeInMillis()));
        userDao.update(userEntity);
    }

    @Test
    @DataSet(value = "init-user-files/init-users-table.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "user-expected-files/expect-user-remove.yml")
    public void remove() {
        UserEntity userEntity = new UserEntity(1L, 1L, "ryan-adm", "qwerty6",
                "ryan@gmail.com", "Ryan", "Gosling", new Date(19801112));
        userDao.remove(userEntity);
    }

    @Test
    @DataSet(value = "init-user-files/init-users-table.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "init-user-files/init-users-table.yml")
    public void findByLogin() {
        String login = "ryan-adm";
        userDao.findByLogin(login);
    }

    @Test
    @DataSet(value = "init-user-files/init-users-table.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "init-user-files/init-users-table.yml")
    public void findByEmail() {
        String email = "ryan@gmail.com";
        userDao.findByEmail(email);
    }

    @Test
    @DataSet(value = "init-user-files/init-users-table.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "init-user-files/init-users-table.yml")
    public void findById() {
        Long id = 1L;
        userDao.findById(id);
    }

    @Test
    @DataSet(value = "init-user-files/init-users-table.yml", executeScriptsBefore = "sql/create-users-table.sql",
            executeScriptsAfter = "sql/drop-users-table.sql")
    @ExpectedDataSet(value = "init-user-files/init-users-table.yml")
    public void findAll() {
        userDao.findAll();
    }
}