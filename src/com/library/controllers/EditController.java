package com.library.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.beans.Employee;
import com.library.utilities.DatabaseConnection;

/**
 * Servlet implementation class EditController
 */
@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id from servle 111111t"+id);
        Employee existingEmp = getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addemp.jsp");
        request.setAttribute("emp", existingEmp);
        dispatcher.forward(request, response);
 
    }
    
    public Employee getEmployee(int id) throws SQLException {
    	Employee emp = null;
    	  DatabaseConnection dbConnection = new DatabaseConnection();
        String sql = "SELECT * FROM borrower WHERE borrower_id = ?";
         
        try {
			dbConnection.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        PreparedStatement statement = dbConnection.connect.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("f_name");
            String email = resultSet.getString("email");
            String contact= resultSet.getString("phone");
            String address= resultSet.getString("address");
            String card_no = resultSet.getString("card_no");
            emp = new Employee(id,name, contact, address,card_no,email);
        }
         
        resultSet.close();
        statement.close();
         
        return emp;
    }
 
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			showEditForm(request,response);
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
