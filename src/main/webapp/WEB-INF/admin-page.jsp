<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "WEB-INF/custom-tag.tld"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin-page.css"/>
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body style="background-color: #F5F5DC">
<div class="container">
    <h3 style="text-align:right">Welcome, Admin ${lastName}!</h3>
    <p style="text-align:right">Click <a href="${pageContext.request.contextPath}/logout">here</a> to log out</p>

    <div class="block" id="action_form"
         style="left: 20%;margin-left: -150px;position: absolute;width: 1000px;margin-top: 15%;">
        <p><a href="${pageContext.request.contextPath}/addUser" class="btn btn-dark">Add user</a></p>
        <form action="${pageContext.request.contextPath}/welcomeAdmin" method="POST" class="admin-form">
            <table id="customers">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>RoleName</th>
                    <th>Login</th>
                    <th>Email</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Birthday</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${usersList}">
                    <tr>
                        <td>${user.getId()}</td>
                            <%--                            <%- <td>${roleEntity}</td>&ndash;%&gt;--%>
                        <td>${roleName}</td> <!--fix-->
<%--                        <td>${user.getRoleId()}</td> <!--fix-->--%>
                        <td>${user.getLogin()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getBirthday()}</td>
                        <td>
                            <a href="<c:url value="/edit?login=${user.login}"/>" class="btn btn-dark">Edit</a>

                            <button data-bs-toggle="modal" data-bs-target="#${user.login}" class="btn btn-dark mx-2"
                                    <c:if test="${currentUser.login == user.login}">disabled</c:if>>
                                Delete
                            </button>

                            <div class="modal fade" id="${user.login}" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure? You want to delete user with login "${user.login}"..
                                        </div>
                                        <div class="modal-footer">
                                            <a href="<c:url value="/delete?login=${user.login}"/>" class="btn btn-dark">Yes</a>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
</div>
</body>
</html>
