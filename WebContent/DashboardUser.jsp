<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.*"%>
<html lang="en">
<head>
  <title>Dashboard</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="homepageUser/navbar.css">
    <link rel="stylesheet" href="homepageUser/css/main.css">
    <link rel="stylesheet" href="homepageUser/css/responsive.css" media="screen and (max-width: 900px)">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>


<%
	
if(session.getAttribute("user")==null){
      			response.sendRedirect("index.jsp");
}
 
else if(session.getAttribute("addressflag")!=null){
	 
		Integer addressflag=(Integer)session.getAttribute("addressflag");
	
      		if(addressflag==0)
      		{
      			response.sendRedirect("profile.jsp");
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
      <a href="DashboardUser"class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
       
    </div>
    <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
	    <div class="dropdown">
		      <button class="dropbtn" onclick="myFunction()">
		        
			      <span class="glyphicon glyphicon-user"></span>  
			        <%=session.getAttribute("user")%>
			      <span class="glyphicon glyphicon-menu-down"></span>  
		      
		    	</button>
		    <div class="dropdown-content" id="myDropdown"> 
		        <a href="ReportUser">Report</a>
		        <a href="Logout">Log Out</a>
		    </div>
	  	</div> 
	</div>
  </div>
  
</nav>

<% List<Details> books = (ArrayList<Details>)request.getAttribute("book");
 	int i=0;
    for(Details book : books){%>
<!-- <div class="container"> -->
      <div class="thirds clearfix">
            
            <!-- one-third -->
           
            <% if(i%3==0) { %>
            <div class="one-third mobile-collapse">
                <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
               
              <h4>Remaining time:</h4>    
               <p id="demo1"></p>
            </div><!--/one-third-->
            <%}
            else if(i%3==1) { %>
            <div class="one-third one-third-second mobile-collapse">
                
               <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
                <h4>Remaining time:</h4>
				<p id="demo2"></p>
            </div><!--/one-third-->
             <%}else{ %>
            <!-- one-third -->
            <div class="one-third one-third-last mobile-collapse">
               
               <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
                <h4>Remaining time</h4>
                <p  id="demo3"></p>
            </div><!--/one-third-->
            <%}
   			i++;	 }
            %>
         </div>
<input type="hidden" id="flag" name="succ" placeholder="Author Name.." value="<%=i%>">
<h2 id="nodata" style="text-align:center;"></h2>            
<script language="javascript"> 





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

//-----------------------------------------------onpage load starts here to fill up tabel---------------------          
$(document).ready(function(){
	var flag=document.getElementById("flag").value;
	if(flag==0)
	{
		document.getElementById("nodata").innerHTML="No Record Found";
	}
	else
	{
		document.getElementById("nodata").innerHTML="";

		//alert(flag);
	}	
       
});
//-----------------------------------------------onpage load ended here to fill up tabel---------------------    



//---------------------------------------------------take all books issued dates------------------------------------------
	<% 
	String str1="",str2="",str3="";
	books = (ArrayList<Details>)request.getAttribute("book");
 	i=0;
    for(Details book : books){
    	if(i==0)
    	{
    		str1=book.getIssuedDate();
    	}
    	else if(i==1)
    	{
    		str2=book.getIssuedDate();
    	}
    	else
    	{
    		str3=book.getIssuedDate();
    	}
    	i++;
    }
    %>
   var s1="<%=str1%>";
   var s2="<%=str2%>";
   var s3="<%=str3%>";
  
   var i="<%=i%>";
  
//---------------------------------------------------take all books issued dates ended------------------------------------------


//---------------------------------------------------count down for book1 starts here-------------------------------------------------------
	if(s1!="")
	{
		  // Set the date we're counting down to
		var countDownDate= Date.parse(s1)+(330*60*1000)+(7*24*60*60*1000);
		//var countDownDate=new Date().getTime()+(330*60*1000)+(7*24*60*60*1000);
		// Update the count down every 1 second
		//alert(countDownDate);
		//var now1 = new Date().getTime()+(330*60*1000);
		//alert(now1);
		var x1 = setInterval(function() {

		    // Get todays date and time
		    var now = new Date().getTime()+(330*60*1000);
		    
		    // Find the distance between now an the count down date
		    var distance = countDownDate - now;
		    
		    // Time calculations for days, hours, minutes and seconds
		    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		    
		    // Output the result in an element with id="demo"
		    document.getElementById("demo1").innerHTML = days + "d " + hours + "h "
		    + minutes + "m " + seconds + "s ";
		    
		    // If the count down is over, write some text 
		    if (distance < 0) {
		        clearInterval(x1);
		        document.getElementById("demo1").innerHTML = "EXPIRED";
		    }
		}, 1000);

	}	
//---------------------------------------------------count down for book1 ends here-------------------------------------------------------

//---------------------------------------------------count down for book2 starts here-------------------------------------------------------
	if(s2!="")
	{
		  // Set the date we're counting down to
		
		var countDownDate= Date.parse(s2)+(330*60*1000)+(7*24*60*60*1000);
		// var countDownDate=new Date().getTime()+(330*60*1000)+(7*24*60*60*1000);

		//alert(countDownDate);
		// Update the count down every 1 second
		var x2 = setInterval(function() {

		    // Get todays date and time
		    var now = new Date().getTime()+(330*60*1000);
		    
		    // Find the distance between now an the count down date
		    var distance = countDownDate - now;
		    
		    // Time calculations for days, hours, minutes and seconds
		    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
		    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
		    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
		    
		    // Output the result in an element with id="demo"
		    document.getElementById("demo2").innerHTML = days + "d " + hours + "h "
		    + minutes + "m " + seconds + "s ";
		    
		    // If the count down is over, write some text 
		    if (distance < 0) {
		        clearInterval(x2);
		        document.getElementById("demo2").innerHTML = "EXPIRED";
		    }
		}, 1000);

	}	

//---------------------------------------------------count down for book2 ends here-------------------------------------------------------



//---------------------------------------------------count down for book3 starts here-------------------------------------------------------
    if(s3!="")
    {
    	  // Set the date we're counting down to
    	var countDownDate= Date.parse(s3)+(330*60*1000)+(7*24*60*60*1000);
		//var countDownDate=new Date().getTime()+(330*60*1000)+(7*24*60*60*1000);
    	// Update the count down every 1 second
    	var x3 = setInterval(function() {

    	    // Get todays date and time
    	    var now = new Date().getTime()+(330*60*1000);
    	    
    	    // Find the distance between now an the count down date
    	    var distance = countDownDate - now;
    	    
    	    // Time calculations for days, hours, minutes and seconds
    	    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    	    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    	    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    	    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    	    
    	    // Output the result in an element with id="demo"
    	    document.getElementById("demo3").innerHTML = days + "d " + hours + "h "
    	    + minutes + "m " + seconds + "s ";
    	    
    	    // If the count down is over, write some text 
    	    if (distance < 0) {
    	        clearInterval(x3);
    	        document.getElementById("demo3").innerHTML = "EXPIRED";
    	    }
    	}, 1000);

    	
    }	
//---------------------------------------------------count down for book3 ends here-------------------------------------------------------

</script>                   
         
         
         
</body>

</html>