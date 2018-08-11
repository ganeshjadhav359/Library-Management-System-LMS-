  <!DOCTYPE html>
  <html lang="en">
  <head>
    <title>Returned Books</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="IssuedBooks/navbar.css">
    <link rel="stylesheet" href="w3.css">

    
    <link rel="stylesheet" href="IssuedBooks/table.css">

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
			        <a href="IssuedBooksAdmin.jsp">Issued Books</a>
			        <a href="AvailableBook">Available Books</a>
			        <a href="issue_return.jsp">Issue/Return Book</a>
			        <a href="Logout">Log Out</a>
		      	</div>
	    	</div> 
	  	</div>
	   </div>
 </nav>
	<div class="w3-container">
		  <h2>Filter Table</h2>
		  <!-- <p>Search for a name in the input field.</p> -->
		
		  <input class="w3-input w3-border w3-padding" type="text" placeholder="Search for user name.." id="myInput" onkeyup="mySearchFunction()">
		
		  <table class="w3-table-all w3-margin-top" id="myTable">
			    <tr>
				    <th>ISBN No.</th>
				    <th>Book Name</th>
				    <!-- <th>Author</th> -->
				    <th>Category</th>
				    <th>User Name</th>
				    <th>Issued Date</th>
				    <th>Return Date</th>
			  	</tr>
		   
		  </table>
	<h2 id="nodata" style="text-align:center;"></h2>
	
	  <!-- <p><strong>Tip:</strong> Remove toUpperCase() if you want to perform a case-sensitive search.</p> -->
	  <!-- <p>Change tr[i].getElementsByTagName('td')[0] to [1] if you want to search for "Country" (index 1) instead of "Name" (index 0).</p> -->
	</div>




  <script>
//---------------------------------------------filter starts here-----------------------------------          

    function mySearchFunction() {
  var input, filter, table, tr, td, i,flag=0;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[3];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
        flag=1;
      } else {
        tr[i].style.display = "none";
      }
    }
  }
  if(flag==1)
  {
 	   document.getElementById("nodata").innerHTML="";
 	  
  }
   else
   {
 	   document.getElementById("nodata").innerHTML="No Record Found";
 	  
   }	  
}
  //---------------------------------------------filter ends here---------------------------------------------          

 //-----------------------------------------------onpage load starts here to fill up tabel---------------------           
  $(document).ready(function(){
        var flag=0;
         $.ajax({
                 type : "GET",
                 url :"http://10.0.2.250:8080/WebServices/GetService/returned",
                contentType: "application/json; charset=utf-8",
                success : function(response) {
                  console.log(response);
                  var trHTML = '';
                               $.each(response, function (i, response) {
                            	   flag=1;
                                   trHTML += '<tr><td>' + response.isbnNumber + '</td><td>' + response.Bname +
                                   '</td><td>' + response.Category + '</td><td>' +response.email + '</td><td>' 
                                   + response.issuedDate+ '</td><td>' 
                                   + response.returnedDate+ '</td></tr>';
                                   //alert(response.isbnNumber);
                               });
                               if(flag==1){
                               	$("#myTable").append(trHTML);
                              	//$("#customers").show();
                               }
                               else
                               {
                            	   document.getElementById("nodata").innerHTML="No Record Found";
                               }
                   //alert(result.success); // result is an object which is created from the returned JSON
    },
});         
   
});
  //-----------------------------------------------onpage load ends here to fill up tabel---------------------          

          
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
  </script>

  </body>
</html>
