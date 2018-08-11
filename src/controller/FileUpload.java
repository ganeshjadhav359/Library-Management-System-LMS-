package controller;
import dbManager.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import dbManager.DBconnection;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="images1";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
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
		  response.setContentType("text/html;charset=UTF-8");
//---------------------------------------check session started--------------------------------------------		  
		  
		  if(request.getSession().getAttribute("user")==null)
			{
				
					response.sendRedirect("index.jsp");
					return;
			}
		  
//---------------------------------------check session ended--------------------------------------------		  
	        PrintWriter out = response.getWriter();
	        
            String savePath = "C:\\Users\\Administrator\\eclipse-workspace\\Ganesh\\WebContent" + File.separator + SAVE_DIR;
	                
            	File fileSaveDir=new File(savePath);
	                if(!fileSaveDir.exists()){
	                    fileSaveDir.mkdir();
	                }
	                else
	                {
	                	System.out.println("direcotry exits");
	                }
	                
	                String bookname=request.getParameter("bookname");
	                String authorname=request.getParameter("authorname");
	                String category=request.getParameter("category");
	                String quantity=request.getParameter("quantity");
	                String description=request.getParameter("description");
	                String isbn=request.getParameter("isbn");
	               // String lastName=request.getParameter("lastname");
	                Part part=request.getPart("file");
	                String orgfileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
//	                String fileName1=extractFileName(part);
//	                System.out.println(fileName1);
	               // System.out.println(fileName);
//	                System.out.println(firstName);
	                
	                String[] split = orgfileName.split("\\.");
	                String ext = split[split.length - 1];
	                System.out.println(ext);
	                String filename=isbn+"."+ext;
	                String dbImgPath= SAVE_DIR+"/"+filename;
	                part.write(savePath + File.separator +isbn+"."+ext);
	                
	                
	                System.out.println(bookname);
	                System.out.println(authorname);
	                System.out.println(category);
	                System.out.println(quantity);
	                System.out.println(description);
	                System.out.println(isbn);
	                System.out.println(dbImgPath);
	                System.out.println("orgfile naem " +orgfileName);
	                System.out.println("new file name "+filename);
	                try {
						int success=Insertdata(bookname,authorname,category,quantity,description,isbn,dbImgPath);
						if(success==1) {
							request.getSession().setAttribute("bookadded", "succ"); //to check is bookdetails added or not
							response.sendRedirect("addbook.jsp");
						}
						else
						{
							request.getSession().setAttribute("bookadded", "error"); //to check is bookdetails added or not
							response.sendRedirect("addbook.jsp");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                

	}
	
	
	private int Insertdata(String bookname,String authorname,String category,String quantity,String description,String isbn,
			String dbImgPath) throws SQLException {
		try {
		  Connection conn=DBconnection.connect();
		  String sql = "INSERT INTO BookTable (isbnNumber,Bname,AuthorName,AvailableQuantity,IssuedQuantity,Description,ImgPath,Category)" +
			        "VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
		  PreparedStatement preparedStatement = conn.prepareStatement(sql);
		  preparedStatement.setString(1, isbn);
		  preparedStatement.setString(2, bookname);
		  preparedStatement.setString(3, authorname);
		  preparedStatement.setInt(4,Integer.parseInt(quantity));
		  preparedStatement.setInt(5,0);
		  preparedStatement.setString(6, description);
		  preparedStatement.setString(7,dbImgPath);
		  preparedStatement.setString(8,category);
		  preparedStatement.executeUpdate(); 
		return 	1;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	} 

}
