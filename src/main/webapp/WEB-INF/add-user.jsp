<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/add-user.css"/>
    <%--  <script src="validation.js" type="text/javascript"></script>--%>
    <title>Add user</title>
</head>
<body style="background-color: #F5F5DC">
<div class="container">
    <h1>Add user</h1>
    <form name="registration" method="POST" action="${pageContext.request.contextPath}/addUser"
          class="registration-form"
          onsubmit="return formValidation()">
        <table>
            <tr>
                <td><label for="role">Role</label></td>
                <td>
                    <select name="role" id="role">
                        <option value="">Select role</option>
                        <option value="English">admin</option>
                        <option value="Spanish">user</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="login">Login:</label></td>
                <td><label>
                    <input name="login" id="login" placeholder="Login" class="form-control" type="text" required
                           minlength="2" maxlength="20">
                </label></td>
            </tr>

            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" name="password" placeholder="Password" id="password" required minlength="2"
                           maxlength="20"></td>
            </tr>
            <tr>
                <td><label for="confirm_password">Confirm password:</label></td>
                <td><input type="password" placeholder="Confirm Password" id="confirm_password" required minlength="2"
                           maxlength="20"></td>
            </tr>
            <tr>
                <td><label for="email">Email:</label></td>
                <td><input type="email" name="email" id="email" placeholder="Email" required>
            </tr>
            <tr>

                <td><label for="firstname">Firstname:</label></td>
                <td><input type="text" name="firstname" id="firstname" placeholder="Firstname"
                           required pattern="([A-Z][a-zA-Z]*)"></td>
            </tr>


            <tr>

                <td><label for="lastname">Lastname:</label></td>
                <td><input type="text" name="lastname" id="lastname" placeholder="Lastname"
                           required pattern="([A-Z][a-zA-Z]*)"></td>
            </tr>


            <tr>
                <form action="">
                    <td>
                        <label for="birthday">Birthday:</label></td>
                    <td><input type="date" id="birthday" name="birthday" required></td>
                </form>
                </td>

            </tr>

            <tr>
                <td colspan="2"><input type="submit" class="submit" onclick="return Validate()" value="Add"/></td>
            </tr>
            <tr>
                  <td colspan="2"> <a href="${pageContext.request.contextPath}/welcomeAdmin" class="submit">Cancel</a></td>
            </tr>
        </table>
    </form>

</div>
</body>
<script type="text/javascript">
    function Validate() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm_password").value;
        if (password !== confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>
</html>
