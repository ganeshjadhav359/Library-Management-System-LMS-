<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="login/navbar.css">
   <link rel="stylesheet" type="text/css" href="login/container.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse nav-bar">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a href="index.jsp"class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
    </div>
    <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="signup.jsp"><span class="glyphicon "></span> Sign Up</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container">

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

int signup=0;
//out.println(request.getParameter("message"));
if(session.getAttribute("signupmsg")!=null){
	Integer signupflag=(Integer)session.getAttribute("signupmsg");
	//out.println(addressflag);	
		if(signupflag==1)
		{
			signup=1;
			session.setAttribute("signupmsg",0);			
			//return;
		}
		
}
%>


  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100">
        <form class="login100-form validate-form p-l-55 p-r-55 p-t-178" name="login" method="post" onsubmit="return validate();" action="Login">
          <span class="login100-form-title">
            Log In
          </span>
          
          
			<%   if(null!=request.getParameter("errorMessage")) { %>
    	<h4 style="color:red;"><% out.println("email or password is wrong");%></h4>
    	    <% }%>
    	    
          <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
            <input class="input100" type="text" name="email" placeholder="email or admin" required>
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input" data-validate = "Please enter password" >
            <input class="input100" type="password" name="pass" placeholder="Password" required>
            <span class="focus-input100"></span>
          </div>
<!-- 
          <div class="text-right p-t-13 p-b-23">
            <span class="txt1">
              Forgot
            </span>

            <a href="#" class="txt2">
              Password?
            </a>
          </div>
 -->
          <div class="container-login100-form-btn">
            <button class="login100-form-btn">
              Log in
            </button>
          </div>

          <div class="flex-col-c p-t-30 p-b-40">
            <span class="txt1 p-b-9">
              Do not have an account?
            </span>

            <a href="signup.jsp" class="txt3">
              Sign up now
            </a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="register" name="succ" placeholder="Author Name.." value="<%=signup%>">
<script type="text/javascript">

$(document).ready(function(){
	var flag=document.getElementById("register").value;
	if(flag==1)
	{
		//document.getElementById("nodata").innerHTML="No Record Found";" )
		alert("You have been register successfully.Please login now.")
	}
	else
	{
		//document.getElementById("nodata").innerHTML=""
		//alert(flag);
	}	
         
});

function validate()
{
	var form=document.forms.login;
	var regularExpression  = /^[a-zA-Z0-9]*$/;
	//alert(form.pass.value);
	//alert(form.confirmPass.value);
	if(form.pass.value.length<6)
	{
		alert("wrong password");
		return false;
	}
	if(!regularExpression.test(form.pass.value))
	{
		alert("wrong password");
		return false;
	}
	return true;
}
</script>

</body>
</html>
