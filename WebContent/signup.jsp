<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Sign Up</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="signupcss/navbar.css">
  <link rel="stylesheet" type="text/css" href="signupcss/container.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
</head>
<body>

<%
	if(session.getAttribute("addressflag")!=null){
		 
		Integer addressflag=(Integer)session.getAttribute("addressflag");
	
      		if(addressflag==0)
      		{
      			response.sendRedirect("profile.jsp");
      			return;
      		}
      		else
      		{
      			response.sendRedirect("DashboardUser");
      			return;
      		}	
	
 }

else if(session.getAttribute("user")!=null){
	 
		String user=(String)session.getAttribute("user");
	
      		if(user.equals("admin"))
      		{
      			response.sendRedirect("DashboardAdmin.jsp");
      			return;
      		}
      		else
      		{
      			response.sendRedirect("DashboardUser");
      			return;
      		}	
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
      <a href="index.jsp" class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
    </div>
    <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login.jsp"><span class="glyphicon "></span> Log In</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container">
      
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100">
        <form name="signup" class="login100-form validate-form p-l-55 p-r-55 p-t-178" method="post" action="Signup" onsubmit="return validate();">
          <span class="login100-form-title">
            Sign Up
          </span>

		
                  <%
                 // out.println(request.getParameter("errorMessage"));
    if(null!=request.getParameter("errorMessage"))
    { %>
    	<h4 style="color:red;"><% out.println("user name is already taken");%></h4>
    	    <% }
%>

          <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
          
            <input class="input100" type="email" name="email" placeholder="Enter email" required>
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input m-b-16" data-validate = "Please enter password">
            <input class="input100" type="password" name="Pass" placeholder="Password" required>
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input">
       <input class="input100" type="password" name="confirmPass" placeholder="Confirm Password" required>
            <span class="focus-input100"></span>
          </div>

          <div class="container-login100-form-btn m-t-16">
            <button class="login100-form-btn" type="submit">
              Sign Up
            </button>
          </div>

          <div class="flex-col-c p-t-30 p-b-40">
            <span class="txt1 p-b-9">
              Already have an account?
            </span>

            <a href="login.jsp" class="txt3">
              Log In now
            </a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
function validate()
{
	var form=document.forms.signup;
	var regularExpression  = /^[a-zA-Z0-9]*$/;
	//alert(form.Pass.value);
	//alert(form.confirmPass.value);
	if(form.Pass.value.length<6)
	{
		alert("password should be 6 character long");
		return false;
	}
	if(form.Pass.value!=form.confirmPass.value)
	{
		alert("password is not matching");
		return false;	
	}
	if(!regularExpression.test(form.Pass.value))
	{
		alert("passwrod should contain only alphabets and numbers");
		return false;
	}
	return true;
}
</script>
</body>
</html>
