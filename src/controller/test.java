package controller;
import dbManager.*;
import controller.StoreController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class test
 */
@WebServlet("/index.jsp")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Connection conn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at:").append(request.getContextPath());
		//request.setAttribute("name", "Hussein Terek");
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		  // Set response content type
		if(request.getSession().getAttribute("user")!=null) 
		{
			System.out.println(request.getSession().getAttribute("addressflag"));
			if(request.getSession().getAttribute("addressflag")!=null)
			{
					Integer addressflag=(Integer)request.getSession().getAttribute("addressflag");
			
		      		if(addressflag==1)
		      		{
		      			response.sendRedirect("DashboardUser.jsp");
		    			
		      		}
		      		else
		      		{
		      			response.sendRedirect("profile.jsp");
		      		}	
		      		return;
			}
			else
			{
				response.sendRedirect("DashboardAdmin.jsp");
			}
		}
		else 
		{
			StoreController bd=new StoreController();
			List<BookDetails> bookdetails=bd.getBookDetails();
			for(BookDetails model : bookdetails) {
	            System.out.println(model.getBname());
	        }
			request.setAttribute("book", bookdetails);
			request.setAttribute("name", "Hussein Terek");
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("name", "Hussein Terek");
		request.getRequestDispatcher("homepage.jsp").forward(request, response);
	}
	

}
