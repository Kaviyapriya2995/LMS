package com.library.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.beans.Employee;
import com.library.utilities.Constants;
import com.library.utilities.DatabaseConnection;
import com.library.utilities.STATUS_TYPE;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public Boolean emailvalidate(String email)
    {
    	 String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    	  
    	    Boolean b = email.matches(EMAIL_REGEX);
    	  
    	    return b;
    }
   
  
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
      private void registerUser(HttpServletRequest request, HttpServletResponse response)
              throws SQLException, IOException {
    	  String uname=request.getParameter("uname");
    	  String pwd=request.getParameter("pwd");
          String name = request.getParameter("name");
          String email = request.getParameter("email");
          String address = request.getParameter("address");
          String contact = request.getParameter("contact");
          String role="normal";
          String card_no = getSaltString();
          
          Boolean status=emailvalidate(email);
          System.out.println("1111111111111111111111111"+status);
          if(!status)
          {
        	  RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
              request.setAttribute(Constants.HAS_STATUS, true);
              request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
              request.setAttribute(Constants.STATUS_HEADER, "Email invalid");
              request.setAttribute(Constants.STATUS_BODY, "Email id format is invalid");
              try {
      			rd.forward(request, response);
      		} catch (ServletException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
          }
      
         // System.out.println("id from servlet name "+name);
          DatabaseConnection dbConnection = new DatabaseConnection();
          String sql_check="select count(*) from borrower where email=? OR username=?";
          String sql = "insert into borrower(f_name,email,address,phone,card_no,username) values (?,?,?,?,?,?)";
         String sql1="insert into users(username,password,user_role) values(?,?,?)";
          try {
  			dbConnection.openConnection();
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
         PreparedStatement statement3 = dbConnection.connect.prepareStatement(sql_check);
         statement3.setString(1, email);
         statement3.setString(2, uname);
          dbConnection.resultSet = statement3.executeQuery();
         
          dbConnection.resultSet.next();
          if( dbConnection.resultSet.getInt(1) != 0)
          {
        	  RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
              request.setAttribute(Constants.HAS_STATUS, true);
              request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
              request.setAttribute(Constants.STATUS_HEADER, "UNAME EXISTS");
              request.setAttribute(Constants.STATUS_BODY, "uname or email already exists");
              try {
      			rd.forward(request, response);
      		} catch (ServletException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
          }
          PreparedStatement statement = dbConnection.connect.prepareStatement(sql);
          statement.setString(1,name );
          statement.setString(2, email);
          statement.setString(3, address);
          statement.setString(4, contact);
        
          statement.setString(5, card_no);
          statement.setString(6,uname );
           
          statement.executeUpdate();
        
          statement.close();
         
            
          PreparedStatement statement1 = dbConnection.connect.prepareStatement(sql1);
          statement1.setString(1,uname );
          statement1.setString(2, pwd);
          statement1.setString(3, role);
        
          statement1.executeUpdate();
        
          statement1.close();
          RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
          request.setAttribute(Constants.HAS_STATUS, true);
          request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
          request.setAttribute(Constants.STATUS_HEADER, "Membership added");
          request.setAttribute(Constants.STATUS_BODY, "Your card No is "+""+card_no);
          try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			registerUser(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
