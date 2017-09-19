package com.library.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.beans.Employee;
import com.library.utilities.DatabaseConnection;

/**
 * Servlet implementation class ListUserController
 */
@WebServlet("/ListUserController")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listemp =listAllEmployees();
        request.setAttribute("listEmp", listemp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Userdetails.jsp");
        dispatcher.forward(request, response);
    }
    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listUser = new ArrayList<>();
        DatabaseConnection dbConnection = new DatabaseConnection();
        String sql = "SELECT * FROM borrower";
         

		   try {
			dbConnection.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
         
        Statement statement = dbConnection.connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("borrower_id");
            String name = resultSet.getString("f_name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String cardno = resultSet.getString("card_no");
            String contact = resultSet.getString("phone");
            Employee emp = new Employee(id,name, contact,address, cardno,email);
            listUser.add(emp);
        }
         
        resultSet.close();
        statement.close();
         
       
         
        return listUser;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			listBook(request,response);
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
