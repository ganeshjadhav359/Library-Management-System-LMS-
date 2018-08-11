package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AvailableBook
 */
@WebServlet("/AvailableBook")
public class AvailableBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvailableBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//---------------------------------------check session started--------------------------------------------		  
		  
		 if(request.getSession().getAttribute("addressflag")!=null){
			 
				Integer addressflag=(Integer)request.getSession().getAttribute("addressflag");
			
		      		if(addressflag==0)
		      		{
		      			response.sendRedirect("profile.jsp");
		      			return;
		      		}
		      		else
		      		{
		      			response.sendRedirect("DashboardUser");
		      			return;
		      		}	
			
		 }
		 else if(request.getSession().getAttribute("user")!=null){
			 
				String user=(String)request.getSession().getAttribute("user");
		   		if(!user.equals("admin"))
		   		{
		   			response.sendRedirect("DashboardUser");
		   			return;
		   		}	
		} 
		 else if(request.getSession().getAttribute("user")==null) {
			 response.sendRedirect("index.jsp");
	   			return;
		 }  
	   //---------------------------------------check session ended--------------------------------------------

		
		
		StoreController bd=new StoreController();
		List<BookDetails> bookdetails=bd.getBookDetails();
		for(BookDetails model : bookdetails) {
            System.out.println(model.getBname());
        }
		request.setAttribute("book", bookdetails);
		request.getRequestDispatcher("AvailablebooksAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
