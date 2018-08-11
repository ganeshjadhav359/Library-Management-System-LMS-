<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <title>Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="DashboardAdmin/navbar.css">
   
    <link rel="stylesheet" href="DashboardAdmin/container.css">
    <link rel="stylesheet" href="DashboardAdmin/findbook.css">
    <link rel="stylesheet" href="DashboardAdmin/table.css">

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
      		}
      		else
      		{
      			response.sendRedirect("DashboardUser.jsp");
      		}	
	
 }

else if(session.getAttribute("user")==null){
		
      		response.sendRedirect("index.jsp");
      		
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
	        <a href="DashboardAdmins.jsp" class="navbar-brand" id="title-css"><span class="title-span">Libray Management </span> System</a>
	          <!-- <a class="navbar-brand" id="" href="#">WebSiteName</a> -->
      </div>
      <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
	        <!-- <ul class="nav navbar-nav"> -->
	          <div class="dropdown">
			        <button class="dropbtn" onclick="myFunction()">
				        <span class="glyphicon glyphicon-user"></span>  
				          Admin
				        <span class="glyphicon glyphicon-menu-down"></span>  
			      </button>
			      <div class="dropdown-content" id="myDropdown">
			        
			        <a href="addbook.jsp">Add Book</a>
			        <a href="AvailableBook">Available Books</a>
			        <a href="returnedBooksAdmin.jsp">Returned Books</a>
			        <a href="IssuedBooksAdmin.jsp">Issued Books</a>
			        <a href="issue_return.jsp">Issue/Return Book</a>
			        <a href="Logout">Log Out</a>
			      </div>
    		</div> 
  
      </div>
    </div>
  </nav>
  <div class="container">
      <div class="inner">
        
		      <a  id="BookClick" class="boxes" style="cursor: pointer; text-decoration: none;">
			        <!-- <img src="book.png" >   -->
			        <i class="fa fa-book     fontAwesome" style="color: #337ab7;
				      font-size: 40px;
				      margin: 0px auto;
				      display: block;
				      text-align: center;
				      padding: 10px 0px;">
			      </i>
			        
				<h2 style="text-align: center;">Find Book</h2>
		      </a>
		      <a  id="UserClick" class="boxes" style="cursor: pointer; text-decoration: none;">
		             <i class="fa fa-user" style="color: #337ab7;
					      font-size: 40px;
					      margin: 0px auto;
					      display: block;
					      text-align: center;
					      padding: 10px 0px;">
				      </i>
		      <h2 style="text-align: center;">Find  User</h2>
		    </a>
     </div>         
  </div>
	<form name="book"> 
				 <div id="book" class=".container">
				      <h2 class="myh2">
				            Search  
				      </h2>
				      <div class="radio">
				        <input type="radio" id="Bname" class="radioLeft" name="radio" value="name"> <h4>By Book Name</h4><br>
				        <span style="margin-right: auto; display: flex;"><input type="radio" id="Bnumber" class="radioRight" name="radio" value="number"><h4 style="margin-left: 10px;">By ISBN Number<br></h4></span>
				        <!-- <input type="radio" name="gender" value="other"> Other -->
				      </div>
				      <div class="searchBox">
				          <input type="text" placeholder="Search.." name="search" required >
				          <button type="submit" onclick="SearchBook()"><i class="fa fa-search" ></i></button>
				      </div>
				</div>
	</form>
		
	<form name="user">
				<div id="user" class=".container">
				      <h2 class="myh2">
				            Search  
				      </h2>
				      <div class="radio">
				        <input type="radio" id="Uname" class="radioLeft" name="radio" value="name"> <h4>By User Name</h4><br>
				        <span style="margin-right: auto; display: flex;"><input type="radio" id="Unumber" class="radioRight" name="radio" value="number"><h4 style="margin-left: 10px;">By Register Number<br></h4></span>
				        <!-- <input type="radio" name="gender" value="other"> Other -->
				      </div>
				      <div class="searchBox">
				          <input type="text" placeholder="Search.." name="search" required>
				          <button type="submit" onclick="SearchUser()"><i class="fa fa-search" ></i></button>
				      </div>
				</div>
	</form>
	<table id="customers">
		  <tr>
			    <th>ISBN No.</th>
			    <th>Name</th>
			    <th>Author</th>
			    <th>Available Quantity</th>
			    <th>Issued Quantity</th>
		  </tr>
	  
	</table>
	<table id="usersTable">
		  <tr>
			    <th>User Name</th>
			    <th>ISBN Number</th>
			    <th>Book Name</th>
			    <th>Issued Date</th>
			    <th>Return/ed Date</th>
		  </tr>
	  
	</table>
<h2 id="nodata" style="text-align:center;"></h2>

  <script>
  
  $(document).ready(function(){
    $("#BookClick").click(function(){
        $("#book").show();
        $("#user").hide();
        $("#customers").hide();
    });
    $("#UserClick").click(function(){
        $("#user").show();
        $("#book").hide();
        $("#customers").hide();
    });
});
//------------------------------------------search book----------------------------------------------------------
function SearchBook() {
    // body...
    			var flag=1;
                  event.preventDefault();
                  var  Bform=document.forms["book"];
                  var str=Bform.radio.value;
                  if(str==""){
                	  alert("please select radio button...");
                	  flag=0;
                  }
                  var search=Bform.search.value;
                  if(search==""){
                	  alert("please enter text to search...");
                	  flag=0;
                  }
                  //Bform.search.value);
                  document.getElementById("nodata").innerHTML="";
                   $("#usersTable").hide();
                  $("#customers").hide(); 
                  //alert("i am here 1 " +str);
                   $("#customers").find("tr:gt(0)").remove();
              
                if(flag==1)
                {
                	var params = {
            			    type:"book",
            			    by:str,
            			    text:search
            			};
                	var flag=0;
            	  //console.log(params);	
            			$.post("Search", $.param(params), function(response) {
            				
            			      		 console.log(response);
            			      		 var trHTML = '';
            			             $.each(response, function (i, response) {
            			            	 flag=1;
            			                 trHTML += '<tr><td>' + response.isbnNumber + '</td><td>' + response.Bname +
            			                 '</td><td>' + response.AuthorName + '</td><td>' +response.AvailableQuantity + '</td><td>' 
            			                 + response.IssuedQuantity+ '</td></tr>';
            			             });
            			             if(flag==1){
            			            	 $('#customers').append(trHTML);
            			             	 $("#customers").show(); 
            			             }
            			             else
            			            {
            			         		document.getElementById("nodata").innerHTML="No Record Found";
            			            } 
            			      	     //alert("Success..");
            			      
            			       
            			    // ...
            			});
                }	  
      }

 //-------------------------------------------search user---------------------------------------------------------------------         
          
          

 function SearchUser() {
		var flag=1;
        event.preventDefault();
        var  Bform=document.forms["user"];
        var str=Bform.radio.value;
        if(str==""){
      	  alert("please select radio button...");
      	  flag=0;
        }
        var search=Bform.search.value;
        if(search==""){
      	  alert("please enter text to search...");
      	  flag=0;
        }
        //Bform.search.value);
        document.getElementById("nodata").innerHTML="";
        $("#usersTable").hide(); 
        //alert("i am here 1 " +str);
         $("#customers").hide(); 
         $("#usersTable").find("tr:gt(0)").remove();
   //alert(str);
   //	alert(search)
      if(flag==1)
      {
      	var params = {
  			    type:"user",
  			    by:str,
  			    text:search
  			};
      	var flag=0;
  	  //console.log(params);	
  			$.post("Search", $.param(params), function(response) {
  				
  			      		 console.log(response);
  			      		 var trHTML = '';
  			             $.each(response, function (i, response) {
  			            	 flag=1;
  			                 trHTML += '<tr><td>' + response.uname + '</td><td>'+ response.isbnNumber + '</td><td>' + response.Bname +
  			                 '</td><td>' +response.issuedDate+ '</td><td>' 
  			                 + response.returneDate+ '</td></tr>';
  			             });
  			             if(flag==1){
  			             	$('#usersTable').append(trHTML);
  			             	$("#usersTable").show(); 
  						}
  			            else
  			            {
			         		document.getElementById("nodata").innerHTML="No Record Found";

  			            }
  			      
  			       
  			    
  			});
      }  
  }

// toggle the dropdown  
  function myFunction() {
      document.getElementById("myDropdown").classList.toggle("show");
  }

  // Close the dropdown if the user clicks outside of it.
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
    