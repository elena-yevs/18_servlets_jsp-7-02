// Select all input elements for verification
const login = document.getElementById("login");
const password = document.getElementById("password");
const confirm_password = document.getElementById("confirm_password");
const email = document.getElementById("email");
const firstname = document.getElementById("firstname");
const lastname = document.getElementById("lastname");
const regexName = /^[a-zA-Z]+ [a-zA-Z]+$/;
const pattern  = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

// function for form verification
function formValidation() {

    // checking name length
    if (login.value.length < 2 || login.value.length > 20) {
        alert("Name length should be more than 2 and less than 21");
        login.focus();
        return false;
    }
    // checking email
    if (!email.value.match(pattern)) {
        alert("Please enter a valid email!");
        email.focus();
        return false;
    }
    // checking password
    if (!password.value.match(/^.{5,15}$/)) {
        alert("Password length must be between 5-15 characters!");
        password.focus();
        return false;
    }

    // checking password
    if (!confirm_password.value.match(/^.{5,15}$/)) {
        alert("Password length must be between 5-15 characters!");
        confirm_password.focus();
        return false;
    }

    // confirm password
    if (password.value !== confirm_password.value) {
        confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
        confirm_password.setCustomValidity('');
    }

    // checking firstname
    if (!regexName.test(firstname)) {
        alert('Invalid name given.');
        firstname.focus();
        return false;
    } else {
        alert('Valid name given.');
    }

    // checking lastname
    if (!regexName.test(lastname)) {
        alert('Invalid name given.');
        lastname.focus();
        return false;
    } else {
        alert('Valid name given.');
    }


    return true;
}