<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <title>Library Management System</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
<body>
    
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Library Management System</a>
                </div>
                     <ul class="nav navbar-nav navbar-right">
                  <li><a href="adminwelcome.html">Home</a></li>
                            <li ><a href="ListUserController">List</a></li>
                          	  <li ><a href="Login.jsp">Logout</a></li>
                          	  </ul>
            </div>
        </nav>
        <br>
        <br>
        <div class="jumbotron">
            <div class="container">
                <h3>Welcome to  Library Management System</h3>
            </div>
        </div>
        
        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">
                    <div class="panel panel-info">
                        <div class ="panel-heading">
                            <h2 align="center">Edit User Details </h2>
                        </div>
                        <div class="panel-body">
                           <form action="UpdateController" method="post">
    						
   
                    <input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
                     
           <div class="form-group">
    						  <label for="usr">Name:</label>
    							  <input type="text" class="form-control" name="name" size="45"
                            value="<c:out value='${emp.name}' />"
                        />
   							 </div>

            
               <div class="form-group">
    						  <label for="usr">Email:</label>
                    <input type="text"  class="form-control" name="email" size="45" 
                            value="<c:out value='${emp.email}' />"
                    />
                    </div>
                      <div class="form-group">
    						  <label for="usr">Contact:</label>
              
                    <input type="text" class="form-control" name="contact" size="15"
                            value="<c:out value='${emp.contact}' />"
                    />
                </div>
                <div class="form-group">
    						  <label for="usr">Address:</label>
                
                    <input type="text" class="form-control"  name="address" size="45"
                            value="<c:out value='${emp.address}' />"
                    />
              </div>
                 <div class="form-group">
    						  <label for="usr">Card No:</label>
                    <input type="text" class="form-control" name="cardno" size="45"
                          readonly  value="<c:out value='${emp.cardno}' />"
                    />
               </div>
              
                     <button type="submit" align="center" class="btn btn-primary">Save</button>
              
        </form>
    </div>   
         </div>
                </div>     
                
            </div>
        </div> 
</body>
</html>