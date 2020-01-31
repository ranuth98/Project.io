function validate(userForm){
var firstName = document.forms["userForm"]["firstName"].value.trim();
var gender = document.forms["userForm"]["gender"].value.trim();
var userId = document.forms["userForm"]["userId"].value.trim();
var contact = document.forms["userForm"]["contact"].value.trim();
var enterPassword = document.forms["userForm"]["enterPassword"].value.trim();
var confirmPassword = document.forms["userForm"]["confirmPassword"].value.trim();
if(firstName == "")
    {
        window.alert("FirstName is required.");
        document.forms["userForm"]["firstName"].style.borderColor = "red";
        document.forms["userForm"]["firstName"].focus();
        return false;
    }
    else if(!firstName.match(/^[A-Za-z]+$/)){
        window.alert("Enter Proper Name without Numbers");
        document.forms["userForm"]["firstName"].style.borderColor = "red";
        document.forms["userForm"]["firstName"].focus();
        return false;
    }
if(gender == 0)
    {
    document.forms["userForm"]["gender"].style.borderColor = "red";
        window.alert("Select gender");
        return false;
    }

    
    if(userId == "")
    {
        window.alert("User Id is required.");
        document.forms["userForm"]["firstName"].style.borderColor = "red";
        document.forms["userForm"]["firstName"].focus();
        return false;
    }
    
    
if(enterPassword == "")
    {
        window.alert("Password is required.");
        document.forms["userForm"]["enterPassword"].style.borderColor = "red";
        document.forms["userForm"]["enterPassword"].focus();
        return false;
    }
    else if(enterPassword.length < 6 || enterPassword.length > 15)
    {
        window.alert("Password should have 6 to 15 characters.");
        document.forms["userForm"]["enterPassword"].style.borderColor = "red";
        document.forms["userForm"]["enterPassword"].focus();
        return false;
    }

    if(enterPassword != confirmPassword){
        window.alert("Confirm Password not matching Password");
        document.forms["userForm"]["confirmPassword"].style.borderColor = "red";
        document.forms["userForm"]["confirmPassword"].focus();
        return false;
    }

}

function preventBack(){window.history.forward();}
setTimeout("preventBack()", 0);
window.onunload=function(){null};