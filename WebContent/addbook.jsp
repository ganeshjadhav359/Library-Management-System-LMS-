<!DOCTYPE html>
<html lang="en">
<head>
  <title>Add book</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="addcss/navbar.css">
 
  <link rel="stylesheet" href="addcss/container.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- <link rel="stylesheet" href="Dashboard/responsive.css" media="screen and (max-width: 900px)"> -->
 <!--  -->
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
	String	 succ_error=null;	
	if(session.getAttribute("bookadded")!=null)
	{
		succ_error=(String) session.getAttribute("bookadded");
		session.setAttribute("bookadded","");
	}
	//succ_error="hi here is me";
	//out.println(succ_error);
%>
<input type="hidden" id="succ_error" name="succ" placeholder="Author Name.." value="<%= succ_error%>">
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
		        <a href="IssuedBooksAdmin.jsp">Issued Books</a>
		        <a href="returnedBooksAdmin.jsp">Returned Books</a>
		        <a href="AvailableBook">Available Books</a>
		        <a href="issue_return.jsp">Issue/Return Book</a>
		        <a href="Logout">Log Out</a>
	      	</div>
    	</div> 
  	</div>
   </div>
 </nav><div>
     <form name="isbn" >
       <span class="myinput">Enter ISBN Number:
          <div style="display: flex;">
            <input type="text" name="isbnNumber" placeholder="XXXXX"/>
            <br>
            <button style="background: #4CAF50;color: #fff" type="submit" onclick="SearchBook()" class="btn btn-default">Submit</button>
          </div>
       </span>
     </form>

      <form id="addquantity" name="quantity">
       <span class="myinput">Enter Quantity:
          <div id="AddQuantity">
            <input type="text" name="bquantity">
            <button style="background: #4CAF50;color: #fff" type="submit" onclick="addquantity()" class="btn btn-default">Submit</button>
          </div>
       </span>
     </form>
</div> 

<div class="container">
		  <form action="FileUpload" name="BookDetails" id="addbook" enctype="multipart/form-data" method="post" onsubmit="return validate();">
			    <div class="row">
			      
			        <label for="fname">Book Name</label>
			        <input type="text" id="fname" name="bookname" placeholder="Book Name.." required>
			      
			    </div>
			    
			    <div class="row">
			      
			        <label for="lname">Author Name</label>
			        <input type="text" id="lname" name="authorname" placeholder="Author Name.." required>
			     
			    </div>
				<div class="row">
			      
			        <input type="hidden" id="lname" name="isbn" placeholder="Author Name.." value="">
			     
			    </div>
			    <div class="row">
			     
			        <label for="lname">Category</label>
			        <input type="text" id="lname" name="category" placeholder="Enter Category.." required>
			     
			    </div>
			
			     <div class="row">
			      
			        <label for="lname">Quantity</label>
			        <input type="text" id="lname" name="quantity" placeholder="Enter Quantity.." required>
			      
			    </div>
			
			     <div class="row">
			      
			        <label for="subject">Description</label>
			        <textarea id="subject" name="description" placeholder="Write something.." style="height:150px" required></textarea>
			      
			    </div>
				<div class="row">
			      
			        <label for="lname">Upload Image</label>
			        <input type="file" id="lname" name="file" placeholder="selecet image.." required>
			      
			    </div>  
			     
			    <div class="row">
			      	<input type="submit" value="Submit">
			    </div>
			    
		  </form>
		</div>
		
<script>
var bookflag=0;
var saveisbn;
$(document).ready(function(){ 
        $("#addbook").hide();
        $("#addquantity").hide();
        //$("#customers").hide();
        var succ_error=document.getElementById("succ_error").value;
       // alert(succ_error);
        if(succ_error!=null){
	    	if(succ_error.localeCompare("succ")==0)
	    	{
	    		alert("successfully inserted book....");
	    	}
	    	if(succ_error.localeCompare("error")==0){
	    		alert("error in inserted book....");	
	    	}
	    	//alert(succ_error);
        }
   
});  
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
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
//----------------------search book is there or not----------------
function SearchBook(){
  event.preventDefault();
  $.ajaxSetup({ cache: false });
  var form=document.forms.isbn;
  var val=form.isbnNumber.value;
  //alert(val);
  $("#addbook").hide();
  $("#addquantity").hide();
  saveisbn=val;
  var params = {
		    isbn: val
		};

		$.post("IsAdded", $.param(params), function(response) {
			
		      	if(response.book==1)
		      	{
		      	        $("#addquantity").show();
		      	       
		      	}
		      	else
		      	{
		      		$("#addbook").show();
		      	}
		       
		    // ...
		});
  // form.isbn=form.isbnNumber.value;
}

//-----------------------add book quantity------------------------------------------------------
function addquantity(){
	event.preventDefault();
	  $.ajaxSetup({ cache: false });
	  var form=document.forms.quantity;
	  var val=form.bquantity.value;
	  if(isNaN(val)){
		  alert("please enter number");
	  }
	  else{
	  val=parseInt(val);	  
	  alert(val);
	  if(val<1){
		  alert("quanttiy should be greater than 0");
	  }	  
	}
	  alert(saveisbn);
	  var params = {
			    isbn: saveisbn,
			    quantity: val
			};
	  //console.log(params);	
			$.post("AddCopies", $.param(params), function(response) {
				
			      	if(response.book==1)
			      	{
			      	        alert("Success..");
			      	       
			      	}
			      	else
			      	{
			      		 alert("not Success..");
			      	}
			       
			    // ...
			});
}

//----------------------------add book details--------------------------------------------------

function validate()
{
		var form=document.forms.BookDetails;
		form.isbn.value=saveisbn;
		console.log(form.isbn.value);
		var val=form.quantity.value;
		  if(isNaN(val)){
			  alert("please enter number");
			  return false;
		  }
		  else{
		  val=parseInt(val);	  
		 //alert(val);
		  if(val<1){
			  alert("quanttiy should be greater than 0");
			  return false;
		  }	 
		} 
		
		var len=form.description.value.length; 
		if(len>350){
			alert("charachters should be less than 350");
			return false;
		}	
		return true;
		  
}

</script>

</body>
</html>
