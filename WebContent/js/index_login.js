function loginValidate() {
		var userName = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if (userName.length <= 0 || userName == null || userName == "") {
			window.alert("User name is required");
			document.getElementById("username").focus();
			document.getElementById("username").style.borderColor = "red";
			return false;
		} else if (userName.length > 50) {
			window.alert("User name not more than 50");
			document.getElementById("username").focus();
			document.getElementById("username").style.borderColor = "red";
			return false;
		} else {
			document.getElementById("username").style.borderColor = "green";
		}

		if (password.length < 6 || password == null || password == "") {
			window.alert("Password is required minimum of 6 characters");
			document.getElementById("password").focus();
			document.getElementById("password").style.borderColor = "red";
			return false;
		} else if (password.length > 15) {
			window.alert("Password is not more than 15 characters");
			document.getElementById("password").focus;
			document.getElementById("password").style.borderColor = "red";
			return false;
		} else {
			document.getElementById("password").style.borderColor = "green";
		}
	}

function preventBack(){window.history.forward();}
setTimeout("preventBack()", 0);
window.onunload=function(){null};
