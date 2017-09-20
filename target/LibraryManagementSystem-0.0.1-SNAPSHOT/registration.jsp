<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="com.library.utilities.STATUS_TYPE"%>
<%@page import="java.util.List"%>
<%@page import="com.library.beans.SearchBookResultBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.utilities.Constants"%>
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
    <script>
function validateForm() {
    var x = document.forms["myForm"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}
</script>
<body>
     <%!
                boolean hasData = false;
                boolean hasStatus = false;
            %>

            <%
                if (request.getAttribute(Constants.BOOK_SEARCH_JSP_REQ) != null) {
                    hasData = true;
                } else {
                    hasData = false;
                }
                if (request.getAttribute(Constants.HAS_STATUS) != null) {
                    if ((Boolean) request.getAttribute(Constants.HAS_STATUS) == true) {
                        hasStatus = true;
                    }
                } else {
                    hasStatus = false;
                }
            %>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Library Management System</a>
                </div>
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
                <div class="col-sm-8 col-sm-offset-3">
                    <div class="panel panel-info">
                        <div class ="panel-heading">
                            <h2 align="center">Register Here </h2>
                        </div>
                        <div class="panel-body">
                           <form name="myForm" action="RegisterController" method="post">
    						
   
                
                     
           <div class="form-group">
    						  <label for="usr">User Name:</label>
    							  <input type="text" class="form-control" name="uname" size="45" required
                            value="<c:out value='${emp.name}' />"
                        />
   							 </div>
 			 <div class="form-group">
    						  <label for="usr">Password:</label>
    							  <input type="password" class="form-control" name="pwd" size="45" required
                            value="<c:out value='${emp.name}' />"
                        />
   							 </div>
             <div class="form-group">
    						  <label for="usr">Name:</label>
    							  <input type="text" class="form-control" name="name" size="45" required
                            value="<c:out value='${emp.name}' />"
                        />
   							 </div>
               <div class="form-group">
    						  <label for="usr">Email:</label>
                    <input type="text"  class="form-control" name="email" size="45" required
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
              
               
                 <center>    <button type="submit"  class="btn btn-primary">Save</button></center>
              
        </form>
           <% if (hasStatus) {%>
                    <h5>
                        <% if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.ERROR) {
                        %>
                        <div class="alert alert-danger" role="alert">
                            <strong>
                                <%= request.getAttribute(Constants.STATUS_BODY)%>
                            </strong>
                        </div>
                        <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.WARNING) {
                        %>
                        <div class="alert alert-info" role="alert">
                            <strong>
                                <%= request.getAttribute(Constants.STATUS_BODY)%>
                            </strong>
                        </div>
                        <% } else if (request.getAttribute(Constants.STATUS_TYPE) == STATUS_TYPE.SUCCESS) {
                        %>
                        <div class="alert alert-success" role="alert">
                            <strong>
                                <%= request.getAttribute(Constants.STATUS_BODY)%>
                            </strong>
                        </div>
                        <% }%>
                    </h5>
                    <%}%>
    </div>   
         </div>
                </div>     
                
            </div>
        </div> 
</body>
</html>