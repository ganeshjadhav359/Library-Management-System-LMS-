package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbManager.DBconnection;

/**
 * Servlet implementation class IssueReturn
 */
@WebServlet("/IssueReturn")
public class IssueReturn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueReturn() {
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
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
//---------------------------------------check session started--------------------------------------------		  
		  
		  if(request.getSession().getAttribute("user")==null)
			{
				
					response.sendRedirect("index.jsp");
					return;
			}
		  
//---------------------------------------check session ended--------------------------------------------

		
		
		
		
		//flag-0 Please register yourself
		  //flag-1 Please fill up your details
		  //flag-2 you have already taken this book
		  //flag-3 you have been already issued 3 books
		 //flag-4  book is not available
		  //flag-5 successfully issued book
		  //flag-6 successfully returned book
		//flag-7 sql error
		int flag=-1;
		    try
		    {
		    	
		    	int addressflag=3;
		    	String type=request.getParameter("type");
				String isbn=request.getParameter("isbnNumber");
				int  uid=Integer.parseInt(request.getParameter("uid"));
				Connection conn = DBconnection.connect();
	//-------------------------------------------find address flag-----------------------------------------------------------	    
		      // create the java mysql update preparedstatement
		      String query = "select addressflag from usertable  where userid= ?";
		      //String query = "update users set num_points = ? where first_name = ?";
		      PreparedStatement preparedStmt= conn.prepareStatement(query);
		      preparedStmt.setInt(1,uid);
    
		     ResultSet rs=preparedStmt.executeQuery();
		     int ifuser=0;
		     while (rs.next()) {
					ifuser=1;
					addressflag=rs.getInt("addressflag");
			}
		    if(ifuser==0) 
		    {
		    	flag=0;
		    }
		     else if(addressflag==0)
		    {
		    	flag=1;
		    }	
		    System.out.println(addressflag); 
	//--------------------------------------------------------------find address flag ended here----------------------------------	     
		
		     
  //-----------------------------------------------------find available, issued quantity and bid ------------------------ 
		     query = "select availablequantity,bid,issuedquantity from booktable  where isbnnumber= ?";
		      //String query = "update users set num_points = ? where first_name = ?";
		      preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString(1,isbn);
		     
		      rs=preparedStmt.executeQuery();
		     int quant=0;
		     int bid=0;
		     int issued=0;
		     while (rs.next()) {
		    	 	bid=rs.getInt("bid");
					quant=rs.getInt("availablequantity");
					issued=rs.getInt("issuedquantity");

			}
		     System.out.println(quant);
		     System.out.println(bid);
		     System.out.println(issued);
//-----------------------------------------------------find available, issued quantity and bid  ended here ------------------------ 
		     
		     
		     
		      if(type.equals("issue") && flag==-1)
		     {
		    	 System.out.println("hello");
		    	 
		    	 //------------------to check user has issued 3 books already and not againg asking for same book --------------  	 
			      query = "select booktable.isbnnumber from issuedbooks ,booktable where issuedbooks.uid=? and booktable.bid=issuedbooks.bookid";
			      preparedStmt = conn.prepareStatement(query);
			     
			      preparedStmt.setInt(1,uid);
				  int f=0;
			      rs=preparedStmt.executeQuery();
			      int cnt=0;
			      while (rs.next()) {
						cnt=cnt+1;
						if(isbn.equals(rs.getString("isbnnumber")))
						{
							f=1; //means asking for issuing same book.
							System.out.println("entered isbn"+isbn+"table isbn "+rs.getString("isbnnumber"));
						}
				}
			      
			   
			      
				 if(quant==0) {
				    	flag=4;//book is not available
				 }
			    else if(f==1)  
			    {
			    	flag=2;			    
			    						//not againg asking for same book ended--------------  
			    }
			    else if(cnt==3)
			    {
			    	flag=3;// he/she has already taken 3 books
			    }
			    else
			    {
			    	
				    
				    	//--------------------------------------date conversion here---------------------------------
				    	 	Long time =new Date().getTime()+(330*60*1000);
					        time =time-time%(24*60*60*1000);
						//	System.out.println("Date represented is "+ time );
					    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a Z");
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					        //TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
					        //Date d = new Date();
					        //System.out.println("Current date is " + d);

					        //sdf.setTimeZone(istTimeZone);
					        String strtime = sdf.format(time);
					        System.out.println("Current date is " + strtime);
					        Date date = sdf.parse(strtime);
					        System.out.println("Current date is " + date);

					        long millis = date.getTime();
					        System.out.println("Current date is " + millis);
					     
					        //--------------------------------------date conversion ended here---------------------------------   
					        
					        
					        
					        String sql = "INSERT INTO issuedbooks(bookid,uid,issueddate)" +
							        "VALUES (?, ?, ?)";
						  PreparedStatement preparedStatement = conn.prepareStatement(sql);
						  preparedStatement.setInt(1, bid);
						  preparedStatement.setInt(2, uid);
						  preparedStatement.setString(3,strtime);
						  preparedStatement.executeUpdate(); 
						 
						  
						  query = "update booktable set availablequantity = ?,issuedquantity=? where isbnnumber= ?";
					      preparedStatement = conn.prepareStatement(query);
					      preparedStatement.setInt(1,(quant-1));
					      preparedStatement.setInt(2,(issued+1));
					      preparedStatement.setString(3,isbn);
					      preparedStatement.executeUpdate();
					      
					      flag=5;
				    	
			    	
			    }
			     
		     }
		     else if(flag==-1){
		    	 
		    	//--------------------------------------date conversion here---------------------------------
		    	 
		    	 Long time =new Date().getTime()+(330*60*1000);
			        time =time-time%(24*60*60*1000);
				//	System.out.println("Date represented is "+ time );
			    //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm a Z");
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			        //TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
			        //Date d = new Date();
			        //System.out.println("Current date is " + d);

			        //sdf.setTimeZone(istTimeZone);
			        String strtime = sdf.format(time);
			        System.out.println("Current date is " + strtime);
			        Date date = sdf.parse(strtime);
			        System.out.println("Current date is " + date);
			        
			      
			        long millis = date.getTime();
			        System.out.println("Current date is " + millis);
			      //--------------------------------------date conversion ended here---------------------------------
			       
			        
			     //-------------------------------------------extract issued date ------------------------------------  
			          query = "select issueddate from issuedbooks where  bookid=? and uid=?";
				      preparedStmt = conn.prepareStatement(query);
				      preparedStmt.setInt(1,bid);
				      preparedStmt.setInt(2,uid);
				      rs=preparedStmt.executeQuery();
				      String issueddate="";
		      while (rs.next()) {
							issueddate=rs.getString("issueddate");
									
					}   
			        //------------------------------------------extract issued date done--------------------------------
		      
		        //------------------------------------------return book entry--------------------------------
      
			        String sql = "INSERT INTO returnedbooks(bookid,uid,issueddate,returneddate)" +
					        "VALUES (?, ?, ?,?)";
				  PreparedStatement preparedStatement = conn.prepareStatement(sql);
				  preparedStatement.setInt(1, bid);
				  preparedStatement.setInt(2, uid);
				  preparedStatement.setString(4,strtime);
				  preparedStatement.setString(3,issueddate);
				  preparedStatement.executeUpdate(); 
			   //------------------------------------------return book entry ended--------------------------------

				  
			   //------------------------------------------delete  book entry from issued table--------------------------------
	  
				  
				   preparedStmt = conn.prepareStatement("DELETE FROM issuedbooks WHERE bookid =? and uid=?");
				   preparedStmt.setInt(1,bid);
			      preparedStmt.setInt(2,uid);
			      preparedStmt.executeUpdate(); 
				  flag=6;
				  
				  query = "update booktable set availablequantity = ?,issuedquantity=? where isbnnumber= ?";
			      preparedStatement = conn.prepareStatement(query);
			      preparedStatement.setInt(1,(quant+1));
			      preparedStatement.setInt(2,(issued-1));
			      preparedStatement.setString(3,isbn);
			      preparedStatement.executeUpdate();
				   //------------------------------------------delete  book entry from issued table--------------------------------
				
		      //conn.close();
			}
			    
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      flag=7;// report sql error
		    }
	
		    
		    Map<String,Integer> options = new LinkedHashMap<>();
		    options.put("status",flag);
//		    
		    String json = new Gson().toJson(options);
		    System.out.println("I am here");
		    System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
		    
		//doGet(request, response);
	}

}
