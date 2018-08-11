package ajaxCall;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import dbManager.DBconnection;
import javafx.util.Pair;

/**
 * Servlet implementation class IsAdded
 */
@WebServlet("/IsAdded")
public class IsAdded extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private String isbn;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsAdded() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
	    list.add("it1");
	    list.add("i2");
		    String json = new Gson().toJson(list);
		    System.out.println("I am here");
		    System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
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
		
		
		
		isbn=request.getParameter("isbn");
		int go=0;
		int flag=0;
		try {
			Connection conn=DBconnection.connect();
			String sql = "select isbnNumber from BookTable where isbnNumber=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			
			System.out.println(" i am here sql query");
			while (rs.next()) {
				flag=1;
				System.out.println(rs.getString("isbnNumber"));
			}
			if(flag==0)
			{
				System.out.println("nothing is there");
			}
			
			go=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(go==1)
		{
			Map<String,Integer> options = new LinkedHashMap<>();
		    options.put("book",flag);
//		    
		    String json = new Gson().toJson(options);
		    System.out.println("I am here");
		    System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}

}
