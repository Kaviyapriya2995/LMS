package com.library.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.beans.CheckoutBean;
import com.library.beans.Employee;
import com.library.utilities.DatabaseConnection;

/**
 * Servlet implementation class ListCheckoutController
 */
@WebServlet("/ListCheckoutController")
public class ListCheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCheckoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void showCheckoutList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id from servle 111111t"+id);
        List<CheckoutBean> listUser= getCheckoutLists(id);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Checkoutlists.jsp");
        request.setAttribute("listUser", listUser);
        dispatcher.forward(request, response);
 
    }
    
    public  List<CheckoutBean> getCheckoutLists(int id) throws SQLException {
    	CheckoutBean chk=null;
        List<CheckoutBean> listUser = new ArrayList<>();
    	  DatabaseConnection dbConnection = new DatabaseConnection();
        String sql = "select b.title,a.card_no,a.due_date from book_loans as a left join book as b on a.book_id=b.book_id where a.borrower_id=?;";
         
        try {
			dbConnection.openConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        PreparedStatement statement = dbConnection.connect.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        while (resultSet.next()) {
            String name = resultSet.getString("title");
            String card_no = resultSet.getString("card_no");
            String due_date= resultSet.getString("due_date");
             chk=new CheckoutBean(name,card_no,due_date);
             listUser.add(chk);
        }
         
        resultSet.close();
        statement.close();
         
        return listUser;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			showCheckoutList(request,response);
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
