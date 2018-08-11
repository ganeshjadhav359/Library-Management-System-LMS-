<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <title>IssueReturnBook</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="issue_return_book/navbar.css">
   
    <link rel="stylesheet" href="issue_return_book/container.css">
    <link rel="stylesheet" href="issue_return_book/findbook.css">
   

     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
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

else if(session.getAttribute("user")==null){
		
      		response.sendRedirect("index.jsp");
      		return;
      		
 }


%>


<%
//out.println("here i am");
if(session.getAttribute("addressflag")!=null){
		 out.println("here i am");
		Integer addressflag=(Integer)session.getAttribute("addressflag");
	
      		if(addressflag==0)
      		{
      			response.sendRedirect("profile.jsp");
      			return;
      		}
      		else
      		{
      			response.sendRedirect("DashboardUser.jsp");
      			return;
      		}	
	
 }
/*else if(null==session.getAttribute("user")){
		
      		response.sendRedirect("index.jsp");
      		return;
 }

*/
%>


<nav class="navbar navbar-inverse nav-bar">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
        </button>
        <a href="DashboardAdmin.jsp"class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
      </div>
      <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">      
      	<div class="dropdown">
	        <button class="dropbtn" onclick="myFunction()"> 
		        <span class="glyphicon glyphicon-user"></span>  
		          Admin
		        <span class="glyphicon glyphicon-menu-down"></span>  
	      	</button>
	      
	      	<div class="dropdown-content" id="myDropdown"> 
		        <a href="DashboardAdmin.jsp">Dashboard</a>
		        <a href="addbook.jsp">Add Book</a>
		        <a href="returnedBooksAdmin.jsp">Returned Books</a>
		        <a href="AvailableBook">Available Books</a>
		        <a href="IssuedBooksAdmin.jsp">Issued Book</a>
		        <a href="Logout">Log Out</a>
	      	</div>
    	</div> 
  	</div>
   </div>
 </nav>    
  <form name="issue_return"> 
 <div id="book" class="container">
     
      <div class="radio">
        <input type="radio" id="Bname" class="radioLeft" name="radio" value="issue"> <h4>Issue</h4><br>
        <span style="margin-right: auto; display: flex;"><input type="radio" id="Bnumber" class="radioRight" name="radio" value="return"><h4 style="margin-left: 10px;">Return<br></h4></span>
        <!-- <input type="radio" name="gender" value="other"> Other -->
      </div>
      <div>
          <h4>Enter ISBN Number</h4>
          <input type="text" name="isbnNumber">
          <h4>UserId</h4>
          <input type="text" name="userId">
          <div>
              <button type="submit" value="submit" onclick="issueReturn()">submit</button>
          </div>
      </div>
</div>

</form>


  <script>
  /* When the user clicks on the button, 
  toggle between hiding and showing the dropdown content */

// toggle the dropdown  
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
  //-------------------------------------issue/ return------------------------------------------------------
  //status-0 Please register yourself
  //status-1 Please fill up your details
  //status-2 you have already taken this book
  //flag-3 you have been already issued 3 books
 //flag-4  book is not available
  //flag-5 successfully issued book
  //flag-6 successfully returned book
//flag-7 sql error
  function issueReturn(){
    // body...
    			event.preventDefault();
    			//alert("please select radio button...");
    			var flag=1;
                  
                  var  Bform=document.forms["issue_return"];
                  var str=Bform.radio.value;
                  if(str==""){
                	  alert("please select radio button...");
                	  flag=0;
                  }
                  var isbn=Bform.isbnNumber.value;
                  if(isbn==""){
                	  alert("please enter text to search...");
                	  flag=0;
                  }
                  var userId=Bform.userId.value;
                  if(userId==""){
                	  alert("please enter text to search...");
                	  flag=0;
                  }
                  //Bform.search.value);
                  //$("#customers").hide(); 
                  //alert("i am here 1 " +str);
                  // $("#customers").find("tr:gt(0)").remove();
var status= ["Please register yourself", "Please fill up your details", "you have already taken this book","you have been already issued 3 books"
	,"book is not available","successfully issued book","successfully returned book","sql error"];
              
                if(flag==1)
                {
                	var params = {
            			    type:str,
            			    isbnNumber:isbn,
            			    uid:userId
            			};
            	  //console.log(params);	
            			$.post("IssueReturn", $.param(params), function(response) {
            				
            			      		 console.log(response);
            			      		 alert(status[response.status]);
            			      		document.getElementById('Bname').checked = false;
            			      		document.getElementById('Bnumber').checked = false;
            			      		Bform.isbnNumber.value="";
            			      		Bform.userId.value="";
            			});
                }	  
            
      }
  </script>

  </body>
</html>
