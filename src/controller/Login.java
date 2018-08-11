package controller;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		 //session.setAttribute("signuperror","");
		 //session.setAttribute("signuperror","");
		HttpSession session=request.getSession();  
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		int addressflag=0;
		int flag=0;
		try {
			Connection conn = DBconnection.connect();
			//----------------------admin login------------------------------------
			if(email.equals("admin")) {
				String sql = "select * from admin where password='"+password+"'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				   while (rs.next()) {
						flag=1;      //---------------password is correct-------------
						//addressflag=rs.getInt(2);
					}
				   if(flag==1)
				   {
						session.setAttribute("user","admin");  
						response.sendRedirect("DashboardAdmin.jsp");
						return;
				   }
				
				
			}
			//---------------------------user login ----------------------------------
			else
			{	
				String sql = "select email,password,addressflag from usertable where email=?";
				System.out.println(sql);
				System.out.println(email);
				System.out.println(password);
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
	
				while (rs.next()) {
					if(password.equals(rs.getString(2)));
					{
						flag=1; //------------grant access as user is authenticated-------------
						addressflag=rs.getInt(3);//-----------get address flag -----------------
					}
				}
								
			}	
			if(flag==1)
			{
				
				session.setAttribute("user",email);  
				session.setAttribute("addressflag",addressflag);  
				if(addressflag==0)
				{
					response.sendRedirect("profile.jsp");
				}
				else
				{
					response.sendRedirect("DashboardUser");

				}
			}
			else// it means we should not grant access to user as well as admin
			{
				response.sendRedirect("login.jsp?errorMessage=error");
			}
			
			//ps.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		//doGet(request, response);
	}

}
