<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Profile</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="profile/MainPage.css">
 
   <link rel="stylesheet" href="profile/container.css">
    <!-- <link rel="stylesheet" href="css/responsive.css" media="screen and (max-width: 900px)"> -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  

  


</head>
<body>

 <%
	
if(session.getAttribute("user")==null){
      			response.sendRedirect("index.jsp");
      			return;
}
 
else  if(session.getAttribute("addressflag")!=null){
	 
		Integer addressflag=(Integer)session.getAttribute("addressflag");
	
      		if(addressflag==1)
      		{
      			response.sendRedirect("DashboardUser");
      			return;
      		}
	
 }
else if(session.getAttribute("user")!=null)
{
		response.sendRedirect("DashboardAdmin.jsp");
		return;
}
%>
<nav class="navbar navbar-inverse nav-bar">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a href="#"class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
    </div>
    <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
        <div class="dropdown">
      <button class="dropbtn" onclick="myFunction()">
        
      <span class="glyphicon glyphicon-user"></span>  
        <% out.println(session.getAttribute("user")); %>
      <span class="glyphicon glyphicon-menu-down"></span>  
    </button>
    <div class="dropdown-content" id="myDropdown"> 
      
      <a href="Logout">Log Out</a>
    </div> 
  </div> 
</div>
    </div>
  </div>
</nav>
<div >
    <h2 class="myh2">Personal Info</h2>
</div>

<div class="container">
  <form action="Register" method="post">
    <div class="row">      
        <label for="fname">Full Name</label>
        <input type="text" id="fname" name="name" placeholder="Your name.." required>
    </div>
    <div class="row">
        <label for="lname">Mobile No</label>
        <input type="text" id="lname" name="number" pattern="[1-9]{1}[0-9]{9}" placeholder="Your mobile number.." required>
    </div>

     <div class="row">
        <label for="subject">Address</label>
        <textarea id="subject" name="address" placeholder="Write address.." style="height:70px" required></textarea>
    </div>

   
   
    <div class="row">
      <input type="submit" value="Submit">
    </div>
  </form>
</div>
         
<script>
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
$(document).ready(function(){
	alert("logged in successfully...");
   alert("Please fill up this form to access the functionality...");
});
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {
    var myDropdown = document.getElementById("myDropdown");
      if (myDropdown.classList.contains('show')) {
        myDropdown.classList.remove('show');
      }
  }
}
</script>	

</body>
</html>
