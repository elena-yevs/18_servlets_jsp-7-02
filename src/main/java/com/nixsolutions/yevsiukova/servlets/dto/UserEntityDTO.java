package com.nixsolutions.yevsiukova.servlets.dto;

import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;

import java.util.Date;

public class UserEntityDTO {

    public UserEntityDTO() {
    }

    public UserEntityDTO(long id, RoleEntity roleEntity, String login, String password, String email, String firstName,
                         String lastName, java.sql.Date birthday) {
        this.id = id;
        this.roleEntity = roleEntity;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    private Long id;
    private RoleEntity roleEntity;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(this.id);
        userEntity.setRoleId(this.roleEntity.getRoleId());
        userEntity.setLogin(this.login);
        userEntity.setPassword(this.password);
        userEntity.setEmail(this.email);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setBirthday(this.birthday);
        return userEntity;
    }

    public Long getRoleId() {
        return roleEntity.getRoleId();
    }

    public String getRoleName() {
        return roleEntity.getRoleName();
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

//    @Override
//    public String toString() {
//        return "UserEntityDTO{" +
//                "id=" + id +
//                ", roleId=" + roleId +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", birthday=" + birthday +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserEntityDTO that = (UserEntityDTO) o;
//        return id.equals(that.id) && roleId.equals(that.roleId) && login.equals(that.login)
//                && password.equals(that.password) && email.equals(that.email)
//                && firstName.equals(that.firstName) && lastName.equals(that.lastName) && birthday.equals(that.birthday);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, roleId, login, password, email, firstName, lastName, birthday);
//    }
}
