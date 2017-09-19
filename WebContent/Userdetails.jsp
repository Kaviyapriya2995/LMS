<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*"%>
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
                 <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="adminwelcome.html">Home</a></li>
                            <li class="active"><a href="#">List</a></li>
                          	  <li ><a href="Login.jsp">Logout</a></li>
                        </ul>
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
       
   <div align="center">
        <table  class="table table-bordered">
            <caption><h2>List of Users</h2></caption>
            <tr>
                
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone No</th>
                 <th>Card No </th>
                    <th>Actions </th>
            </tr>
            <c:forEach var="emp" items="${listEmp}">
                <tr>
                  
                    <td ><c:out value="${emp.name}" /></td>
                     <td ><c:out value="${emp.email}" /></td>
                          <td><c:out value="${emp.address}" /></td>
                    <td ><c:out value="${emp.contact}" /></td>
               
                     <td ><c:out value="${emp.cardno}" /></td>
                    <td>
                        <a href="EditController?id=<c:out value='${emp.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="DeleteController?id=<c:out value='${emp.id}' />">Delete</a>
                         &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="ListCheckoutController?id=<c:out value='${emp.id}' />">Checkout Lists</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>   
</body>
</html>