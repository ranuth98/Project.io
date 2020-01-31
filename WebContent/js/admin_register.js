function adminValidate() {
	var firstName = document.forms["adminForm"]["firstName"].value.trim();
	var lastName = document.forms["adminForm"]["lastName"].value.trim();
	var password = document.forms["adminForm"]["password"].value;
	var confirmPassword = document.forms["adminForm"]["confirmPassword"].value;
	var gender = document.forms["adminForm"]["gender"].value;
	var letters = /^[A-Za-z]+$/;

	if (!(firstName.match(letters))) {
		alert("Name should not have numbers");
		document.forms["adminForm"]["firstName"].style.borderColor = "red";
		document.forms["adminForm"]["firstName"].focus();
		return false;
	}
	if (!(lastName.match(letters))) {
		alert("Name should not have numbers");
		document.forms["adminForm"]["lastName"].style.borderColor = "red";
		document.forms["adminForm"]["lastName"].focus();
		return false;
	}

	if (gender == 0) {
		window.alert("Gender is required");
		document.forms["adminForm"]["gender"].style.borderColor = "red";
		document.forms["adminForm"]["gender"].focus();
		return false;
	}

	if (password.length < 6 || password.length > 15) {
		alert("Password should have 6 to 15 Characters");
		document.forms["adminForm"]["password"].style.borderColor = "red";
		document.forms["adminForm"]["password"].focus();
		return false;
	}
	if (confirmPassword != password) {
		alert("Passwords do not match");
		document.forms["adminForm"]["confirmPassword"].style.borderColor = "red";
		document.forms["adminForm"]["confirmPassword"].focus();
		return false;
	}
}

function preventBack(){window.history.forward();}
setTimeout("preventBack()", 0);
window.onunload=function(){null};