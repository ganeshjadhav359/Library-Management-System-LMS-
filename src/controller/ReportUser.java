package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReportUser
 */
@WebServlet("/ReportUser")
public class ReportUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null) 
		{
			System.out.println(request.getSession().getAttribute("addressflag"));
			if(request.getSession().getAttribute("addressflag")!=null)
			{
					Integer addressflag=(Integer)request.getSession().getAttribute("addressflag");
			
		      		if(addressflag==1)
		      		{
		      			System.out.println("user is not null");
		    			StoreController bd=new StoreController();
		    			List<Details> bookdetails=bd.UserReport((String)request.getSession().getAttribute("user"));
		    			for(Details model : bookdetails) {
		    	            System.out.println(model.getBname());
		    	        }
		    			request.setAttribute("book", bookdetails);
		    			request.setAttribute("name", "Hussein Terek");
		    			request.getRequestDispatcher("ReportUser.jsp").forward(request, response);
		      		}
		      		else
		      		{
		      			response.sendRedirect("profile.jsp");
		      		}	
		      		return;
			}
			else
			{
				System.out.println("dashboard redirect");
				response.sendRedirect("DashboardAdmin.jsp");
				return;
			}
		
	     }
		
		else
		{
			System.out.println("user is null");
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
