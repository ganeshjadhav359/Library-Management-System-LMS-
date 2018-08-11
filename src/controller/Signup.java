package controller;
import dbManager.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbManager.DBconnection;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		if(request.getSession().getAttribute("user")!=null)
		{
			if(request.getSession().getAttribute("addressflag")!=null)
			{
				response.sendRedirect("DashboardUser");
			}
			else
			{	
				response.sendRedirect("DashboardAdmin.jsp");
			}	
			return;
		}
		else{
		String email=request.getParameter("email");
		String password=request.getParameter("Pass");
		int flag=1;
		try {
			Connection conn = DBconnection.connect();

			String sql = "select email from usertable where email='"+email+"'";
			System.out.println(sql);
			System.out.println(email);
			System.out.println(password);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				flag=0;
			}
			if(flag==1)
			{
				  sql = "INSERT INTO usertable (email,Password)" + "VALUES (?, ?)";
				  PreparedStatement preparedStatement = conn.prepareStatement(sql);
				  preparedStatement.setString(1, email);
				  preparedStatement.setString(2, password);
				  preparedStatement.execute();
				  HttpSession session=request.getSession();  
				  session.setAttribute("signupmsg",1);  
				  response.sendRedirect("login.jsp");

				  
			}
			else
			{
				response.sendRedirect("signup.jsp?errorMessage=error");
			}
			//ps.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		
		}
	}		
}
