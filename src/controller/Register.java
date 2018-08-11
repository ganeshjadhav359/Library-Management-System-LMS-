package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbManager.DBconnection;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
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
		//doGet(request, response);
		 try
		    {
			 if(request.getSession().getAttribute("user")==null)
				{	
					response.sendRedirect("Index.jsp");
					return;
				}
			 String user=(String) request.getSession().getAttribute("user");
			 
			 if(user.equals("admin"))
			 {
				 response.sendRedirect("DashboardAdmin.jsp");
				return;
			 }
		     String name=request.getParameter("name");
		     String number=request.getParameter("number");
		     String address=request.getParameter("address");
		     String email=(String)request.getSession(false).getAttribute("user");
		     Connection conn = DBconnection.connect();
		    
		      // create the java mysql update preparedstatement
		      String query = "update usertable set fullname = ?,address= ?,PhoneNumber= ?,AddressFlag= ?"+" where "+"email = ?";
		      //String query = "update users set num_points = ? where first_name = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1,name);
			  preparedStmt.setString(2,address);
			  preparedStmt.setString(3,number);
			  preparedStmt.setInt(4,1);
			  preparedStmt.setString(5,email);
		      preparedStmt.executeUpdate();
		     
		     System.out.println(name);
		     System.out.println(number);
		     System.out.println(address);
		     System.out.println(email);
		     request.getSession(false).setAttribute("addressflag",1);
			  response.sendRedirect("DashboardUser");

			
			    
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
	
	}

}
