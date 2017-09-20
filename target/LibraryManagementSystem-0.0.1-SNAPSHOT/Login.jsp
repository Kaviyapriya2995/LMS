 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@page import="com.library.utilities.STATUS_TYPE"%>
<%@page import="java.util.List"%>
<%@page import="com.library.beans.SearchBookResultBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.utilities.Constants"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
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

       <div class="container" >
            <!-- Example row of columns -->
            <div class="row" >
                <div class="col-sm-6 col-sm-offset-3" >
                    <div class="panel panel-info">
                        <div class ="panel-heading">
                            <h2 align="center">Login </h2>
                        </div>
                        <div class="panel-body">
                      
                           <form action="LoginController" method="post">
    						<div class="form-group">
    						  <label for="usr">User Name:</label>
    							  <input type="text" class="form-control" id="usr" name="usr" required>
   							 </div>
  								  <div class="form-group">
 									     <label for="pwd">Password:</label>
    						  <input type="password" class="form-control" id="pwd" name="pwd" required>
    						</div>
    						
    						   <button type="submit" align="center" class="btn btn-primary">Login</button>
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
						<center> 
			
				<label for="usr" >New User ? &nbsp;<a href="registration.jsp">Request membership</a></label>
			</center>
                           
                        </div>
                    </div>
                </div>     
                
            </div>
        </div> 
    </body>
</html>