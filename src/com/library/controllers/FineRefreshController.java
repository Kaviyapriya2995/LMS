package com.library.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.utilities.Constants;
import com.library.utilities.DatabaseConnection;
import com.library.utilities.STATUS_TYPE;

/**
 * Servlet implementation class FineRefreshController
 */
@WebServlet("/FineRefreshController")
public class FineRefreshController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FineRefreshController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            response.setContentType("text/html;charset=UTF-8");

            dbConnection.openConnection();

            StringBuilder sqlString = new StringBuilder();
            sqlString.append("insert into fines (loan_id, fine_amt) "
                    + "select T.loan_id,T.fine*0.25 from (select loan_id, if(date_in IS NULL,datediff(curdate(),due_date),IF(date_in>due_date,datediff(date_in,due_date),0)) as fine from book_loans having fine>0) as T "
                    + "where T.loan_id NOT IN (select f.loan_id from fines as f);");
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
            dbConnection.preparedStatement.executeUpdate();

            sqlString = new StringBuilder();
            sqlString.append("update fines join (select loan_id, if(date_in IS NULL,datediff(curdate(),due_date),IF(date_in>due_date,datediff(date_in,due_date),0)) as fine from book_loans having fine>0) as T on fines.loan_id = T.loan_id set fines.fine_amt = T.fine*0.25 where fines.paid = 0; ");
            dbConnection.preparedStatement = dbConnection.connect.prepareStatement(sqlString.toString());
            dbConnection.preparedStatement.executeUpdate();

            dbConnection.closeConnection();

            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.SUCCESS);
            request.setAttribute(Constants.STATUS_HEADER, "Refresh ");
            request.setAttribute(Constants.STATUS_BODY, "Refresh Successful");
            rd.forward(request, response);

        } catch (SQLException ex) {
            dbConnection.closeConnection();
            RequestDispatcher rd = request.getRequestDispatcher("FineTracking.jsp");
            request.setAttribute(Constants.HAS_STATUS, true);
            request.setAttribute(Constants.STATUS_TYPE, STATUS_TYPE.ERROR);
            request.setAttribute(Constants.STATUS_HEADER, "Exception caught");
            request.setAttribute(Constants.STATUS_BODY, "MySql exception caught. Please try again. Exception is " + ex.toString());
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
