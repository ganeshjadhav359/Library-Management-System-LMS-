package ajaxCall;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import controller.*;
/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		
//---------------------------------------check session started--------------------------------------------		  
		  
		  if(request.getSession().getAttribute("user")==null)
			{
				
					response.sendRedirect("index.jsp");
					return;
			}
		  
//---------------------------------------check session ended--------------------------------------------
		
		String type=request.getParameter("type");
		String by=request.getParameter("by");
		String text=request.getParameter("text");
		String sql = "select * from BookTable where lower(bname) like lower('%"+text+"%')";
		//System.out.println(sql);
        List<Product> range = new ArrayList<Product>();
        List<BookDetails>  Bsearch= new ArrayList<BookDetails>();
        List<Details>  Usearch= new ArrayList<Details>();
		Map<String,Integer> options = new LinkedHashMap<>();
	    options.put("book",1);
	   // System.out.println(type.equals(new String("book")));
	    String js="";
	    if(type.equals("book"))
	    {
	    	System.out.println(by);
	    	if(by.equals("name"))
	    	{
	    		StoreController sc= new StoreController();
	    		Bsearch=sc.searchByName(text);
	    	}
	    	else
	    	{
	    		StoreController sc= new StoreController();
	    		Bsearch=sc.searchByNumber(text);
	    	}
	    	  js=new Gson().toJson(Bsearch);
	    }
	    else
	    {
	    	System.out.println(by+"useer");
	    	if(by.equals("name"))
	    	{
	    		StoreController sc= new StoreController();
	    		try {
					Usearch=sc.searchUserbyName(text);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		System.out.println(" here is me");
	    	}
	    	else
	    	{
	    		System.out.println("user id block");
	    		int id=Integer.parseInt(text);
	    		StoreController sc= new StoreController();
	    		try {
					Usearch=sc.searchUserbyId(id);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	js=new Gson().toJson(Usearch);
	    }	
//	    
	    
	    System.out.println("I am here at end ");
	    //System.out.println(type);
	    System.out.println(by);
	    //System.out.println(text);
	   
	    //System.out.println(json);
	    System.out.println(js);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(js);
	}

}
