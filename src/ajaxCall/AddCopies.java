package ajaxCall;

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
 * Servlet implementation class AddQuantiy
 */
@WebServlet("/AddCopies")
public class AddCopies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCopies() {
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
		 try
		    {
			 int go=0;
		     String isbn=request.getParameter("isbn");
		     int flag=0;
		     int quant=Integer.parseInt(request.getParameter("quantity"));
		     int oldQuant=0;
		     Connection conn = DBconnection.connect();
		    
		      // create the java mysql update preparedstatement
		      String query = "select AvailableQuantity from booktable  where isbnNumber = ?";
		      //String query = "update users set num_points = ? where first_name = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1,isbn);
		     // preparedStmt.setString(2, "Fred");
		      // execute the java preparedstatement
		     ResultSet rs=preparedStmt.executeQuery();
		     while (rs.next()) {
					flag=1;
					oldQuant=rs.getInt("AvailableQuantity");
				}
		     System.out.println(oldQuant);
		     if(flag==1)
		     {
		    	 System.out.println("hello");
			      query = "update booktable set AvailableQuantity = ?"+" where "+"isbnNumber = ?";
			      preparedStmt = conn.prepareStatement(query);
			      quant=oldQuant+quant;
			      preparedStmt.setInt(1,quant);
				  preparedStmt.setString(2, isbn);
			      preparedStmt.executeUpdate();
			      System.out.println("go");
			      go=1;
		     }
			if(go==1) {
				Map<String,Integer> options = new LinkedHashMap<>();
			    options.put("book",flag);
//			    options.put("value2", "label2");
//			    options.put("value3", "label3");
			    String json = new Gson().toJson(options);
			    System.out.println("I am here");
			    System.out.println(json);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
		      //conn.close();
			}
			    
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		  
	}

}



















