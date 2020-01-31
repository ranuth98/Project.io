function confirmDelete()
{
  var x = confirm("Are you sure you want to delete?");
  if (x)
      return true;
  else
    return false;
}

 function salaryValidate() {
        var salary = document.forms["approve-admin"]["salary"].value.trim();
        var numbers=/^[0-9]+$/;
        
        if (salary == null || salary == "" || salary == 0) {
            alert("Enter Admin's Salary...");
            document.forms["approve-admin"]["salary"].focus();
            return false;
        }    
        if(!(salary.match(numbers))){
            alert("Salary must be in number format.");
            document.forms["approve-admin"]["salary"].focus();
            return false;
        }
    }
 
 function preventBack(){window.history.forward();}
 setTimeout("preventBack()", 0);
 window.onunload=function(){null};