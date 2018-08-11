package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardUser
 */
@WebServlet("/DashboardUser")
public class DashboardUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("user")!=null) {
			StoreController bd=new StoreController();
			List<Details> bookdetails=bd.UserDashboard((String)request.getSession().getAttribute("user"));
			for(Details model : bookdetails) {
	            System.out.println(model.getBname());
	        }
			request.setAttribute("book", bookdetails);
			request.setAttribute("name", "Hussein Terek");
			request.getRequestDispatcher("DashboardUser.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("index.jsp");
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
