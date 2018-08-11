<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.BookDetails"%>
<html lang="en">
<head>
  <title>Homepage</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/navbar.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/responsive.css" media="screen and (max-width: 900px)">
    
    
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
        <!-- <a class="navbar-brand" id="" href="#">WebSiteName</a> -->
    </div>
    <div class="collapse navbar-collapse nav-bar" style="border-color: #39424e;" id="myNavbar">
      <!-- <ul class="nav navbar-nav"> -->
      <ul class="nav navbar-nav navbar-right">
        
        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Log In</a></li>
        <li><a href="signup.jsp"><span class="glyphicon glyphicon-sign-in"></span> Sign In</a></li>

      </ul>
    </div>
  </div>
</nav>
<% List<BookDetails> books = (ArrayList<BookDetails>)request.getAttribute("book");
 	int i=0;
    for(BookDetails book : books){%>
       
       
      <div class="thirds clearfix">
            
            <!-- one-third -->
           
            <% if(i%3==0) { %>
            <div class="one-third mobile-collapse">
                <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
               
                
                <p class="text-justify"><%=book.getDescription()%></p>
            </div><!--/one-third-->
            <%}
            else if(i%3==1) { %>
            <div class="one-third one-third-second mobile-collapse">
                
               <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
                
                <p class="text-justify"><%=book.getDescription()%></p>
            </div><!--/one-third-->
             <%}else{ %>
            <!-- one-third -->
            <div class="one-third one-third-last mobile-collapse">
               
               <div>
                    <img src="<%=book.getImgPath()%>" class="mx-auto d-block rounded" alt="A bird on a fence" />
                </div>
                <h5><%=book.getBname()%></h5>
                <h4><%=book.getCategory()%></h4>
               
                <p class="text-justify"><%=book.getDescription()%></p>
            </div><!--/one-third-->
            <%}
   			i++;	 }
            %>
         </div>   
</body>
</html>