package com.library.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.utilities.Constants;
import com.library.utilities.DatabaseConnection;
import com.library.utilities.STATUS_TYPE;
/**
 * Servlet implementation class FinePaymentController
 */
@WebServlet("/FinePaymentController")
public class FinePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinePaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            if (request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO) == null || request.getParameter(Constants.FINE_PAYMENT_REQ_AMT) == null || request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO).equals("") || request.getParameter(Constants.FINE_PAYMENT_REQ_AMT).equals("")) {
                throw new SQLException();
            }
            String cardNo = request.getParameter(Constants.FINE_PAYMENT_REQ_CARD_NO);
            String amount = request.getParameter(Constants.FINE_PAYMENT_REQ_AMT);

            dbConnection.openConnection();
            String sqlString = "update fines join book_loans on fines.loan_id = book_loans.loan_id set fines.paid = 1 where fines.paid = 0 and book_loans.card_no = ?;";
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString);
            dbConnection.preparedStatement.setString(1, cardNo);
            dbConnection.preparedStatement.executeUpdate();

            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
            request.setAttribute(Constants.STATUS_HEADER, "Payment");
            request.setAttribute(Constants.STATUS_BODY, "Payment of " + amount + " was successfully recorded.");
            rd.forward(request, response);

        } catch (SQLException e) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "MySql exception caught. Please try again. Exception is " + e.toString());
            rd.forward(request, response);

        } catch (ClassNotFoundException ex) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "ClassNotFound exception caught. Please try again. Exception is " + ex.toString());
            rd.forward(request, response);
        } finally {
            dbConnection.closeConnection();
        }

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
