package com.library.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.beans.Employee;
import com.library.utilities.DatabaseConnection;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public boolean updateUserdb(Employee emp) throws SQLException {
  	  DatabaseConnection dbConnection = new DatabaseConnection();
        String sql = "UPDATE borrower SET f_name = ?,email=?,  address = ?, phone= ?,card_no= ?";
        sql += " WHERE borrower_id = ?";
        try {
			dbConnection.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        PreparedStatement statement = dbConnection.connect.prepareStatement(sql);
        statement.setString(1, emp.getName());
        statement.setString(2, emp.getEmail());
        statement.setString(3, emp.getAddress());
        statement.setString(4, emp.getContact());
      
        statement.setString(5, emp.getCardno());
        statement.setInt(6, emp.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
      
        statement.close();
       
        return rowUpdated;     
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String id1=request.getParameter("id");
    	  // System.out.println("id from servlet"+id1);
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        String cardno = request.getParameter("cardno");
     
       // System.out.println("id from servlet name "+name);
        Employee emp = new Employee(id,name, contact, address,cardno,email);
        updateUserdb(emp);
        response.sendRedirect("ListUserController");
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			updateUser(request,response);
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
