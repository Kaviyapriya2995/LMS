package com.library.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.beans.Login;
import com.library.utilities.Constants;
import com.library.utilities.DatabaseConnection;
import com.library.utilities.STATUS_TYPE;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String action = request.getServletPath();
		  System.out.println("action"+action);
		//   String email=(String)session.getAttribute("email");
		/*	session=request.getSession(true);
			session.setMaxInactiveInterval(60);
	        //redirect user to home page if already logged in
			
	      if(session.isNew())
	      {
	    	  if(!action.equals("/login"))
	    	  {
	    		  response.sendRedirect("welcome.jsp");  
	    		  return;
	    	  }
	    	    	 
	      }
	     */
	    	try {
				loginValidate(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	     
	}

	
	private void loginValidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		System.out.println("objj");
		   DatabaseConnection dbConnection = new DatabaseConnection();
		String uname=request.getParameter("usr");
		String pwd=request.getParameter("pwd");
		Login obj=new Login(uname,pwd);
		PrintWriter out = response.getWriter();
		System.out.println("obj"+obj.getUname());
		
		 HttpSession session =request.getSession(true);
		 session.setAttribute("uname",obj.getUname());
		 String sql = "SELECT * FROM users WHERE username = ? AND password=?";
        
         
		   try {
			dbConnection.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sql);
		   dbConnection.preparedStatement.setString(1, uname);
		   dbConnection.preparedStatement.setString(2, pwd);
		   dbConnection.resultSet = dbConnection.preparedStatement.executeQuery();
        if ( dbConnection.resultSet .next()) {
        	  String result = dbConnection.resultSet.getString("user_role");
        	  System.out.println(result);
        	 if(result.equals("admin"))
        			 {
        		 response.sendRedirect("adminwelcome.html"); 
        			 }
        	 else if(result.equals("normal"))
        	 {
        		 response.sendRedirect("welcome.html"); 
        	 }
        	
        }
        else
        {
        	RequestDispatcher rd = request.getRequestDispatcher("adminLogin.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Invalid Credentials");
            request.setAttribute(Constants.STATUS_BODY, "Please enter valid username or password");
            rd.forward(request, response);

        	dbConnection.resultSet.close();
             
        	
        }
      
    }
	
	/*private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
	
	//out.println("thanq you!!, Your session was destroyed successfully!!");
	HttpSession session = request.getSession(false);
	// session.setAttribute("user", null);
	session.removeAttribute("email");
	 response.sendRedirect("index.jsp");
	}*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
