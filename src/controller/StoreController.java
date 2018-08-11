package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.BookDetails;
import dbManager.*;
public class StoreController {
	List<BookDetails>					books	= new ArrayList<BookDetails>();

	private static Connection	conn;
	public List<BookDetails> getBookDetails()
	{
		try {
			conn = DBconnection.connect();

			String sql = "select * from BookTable";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				books.add(new BookDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			}
			//ps.close();
			return books;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BookDetails> searchByName(String text)
	{
		List<BookDetails>					Sbooks	= new ArrayList<BookDetails>();
		try {
			conn = DBconnection.connect();

			String sql = "select * from BookTable where lower(bname) like lower('%"+text+"%')";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Sbooks.add(new BookDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			}
			//ps.close();
			return Sbooks;
				

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public List<BookDetails> searchByNumber(String text)
	{
		List<BookDetails>					Sbooks	= new ArrayList<BookDetails>();
		try {
			conn = DBconnection.connect();

			String sql = "select * from BookTable where lower(isbnNumber) like lower('%"+text+"%')";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Sbooks.add(new BookDetails(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9)));
			}
			//ps.close();
			return Sbooks;
				

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Details> UserDashboard(String text)
	{
		List<Details>	Ubooks	= new ArrayList<Details>();
		try {
			conn = DBconnection.connect();
			//text="ganeshjadhav359@gmail.com";
			String sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, i.issueddate,b.imgpath from usertable u,issuedbooks i" + 			
			",booktable b where u.userid=i.uid and u.email='"+text+"' and b.bid=i.bookid";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Details d=new Details();
				d.setBname(rs.getString(1));
				d.setCategory(rs.getString(2));
				d.setIsbnNumber(rs.getString(3));
				d.setAuthorName(rs.getString(4));
				d.setIssuedDate(rs.getString(5));
				d.setImgPath(rs.getString(6));
				//d.setReturneDate(rs.getString(6));
				Ubooks.add(d);
			}
			//ps.close();
			return Ubooks;
				

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public List<Details> UserReport(String text)
	{
		List<Details>	Ubooks	= new ArrayList<Details>();
		try {
			conn = DBconnection.connect();
			//text="ganeshjadhav359@gmail.com";
			String sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, r.issueddate,r.returneddate from usertable u,returnedbooks r" + 			
			",booktable b where u.userid=r.uid and u.email='"+text+"' and b.bid=r.bookid";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Details d=new Details();
				d.setBname(rs.getString(1));
				d.setCategory(rs.getString(2));
				d.setIsbnNumber(rs.getString(3));
				d.setAuthorName(rs.getString(4));
				d.setIssuedDate(rs.getString(5));
				d.setReturneDate(rs.getString(6));
				Ubooks.add(d);
			}
			//ps.close();
			return Ubooks;
				

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public List<Details>  searchUserbyName(String text) throws ParseException
	{
		List<Details>	Ubooks	= new ArrayList<Details>();
	
		try {
			conn = DBconnection.connect();
			//text="ganeshjadhav359@gmail.com";
			String sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, i.issueddate,u.email from usertable u,issuedbooks i" + 			
				",booktable b where u.userid=i.uid and u.email like ('%" +text +"%') and b.bid=i.bookid";
				System.out.println(sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Details d=new Details();
					d.setBname(rs.getString(1));
					d.setCategory(rs.getString(2));
					d.setIsbnNumber(rs.getString(3));
					d.setAuthorName(rs.getString(4));
					d.setIssuedDate(rs.getString(5));
					d.setUname(rs.getString(6));
					String issue=rs.getString(5);
					//--------------------------------------date conversion to show return date here---------------------------------
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				        Date d1 = format.parse(issue);
				        long time = d1.getTime();
				        System.out.println("Date represented is "+ d1);
				        Date d2 = new Date(time+(7*24*3600*1000));
						//System.out.println("Date represented is "+ d2 );
						String Rdate=format.format(d2);
			        //--------------------------------------date conversion to show return date ended here---------------------------------   

					d.setReturneDate(Rdate);
					Ubooks.add(d);
				}
				//ps.close();
				//return Ubooks;
		//text="ganeshjadhav359@gmail.com";
		sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, r.issueddate,r.returneddate,u.email from usertable u,returnedbooks r" + 			
		",booktable b where u.userid=r.uid and u.email like ('%" +text+ "%') and b.bid=r.bookid";
		System.out.println(sql);
		 ps = conn.prepareStatement(sql);
		 rs = ps.executeQuery();

		while (rs.next()) {
			Details d=new Details();
			d.setBname(rs.getString(1));
			d.setCategory(rs.getString(2));
			d.setIsbnNumber(rs.getString(3));
			d.setAuthorName(rs.getString(4));
			d.setIssuedDate(rs.getString(5));
			d.setReturneDate(rs.getString(6));
			d.setUname(rs.getString(7));
			Ubooks.add(d);
		}
		
		
		//ps.close();
		return Ubooks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
			

	}
	
	
	
	
	public List<Details>  searchUserbyId(int id) throws ParseException
	{
		List<Details>	Ubooks	= new ArrayList<Details>();
	
		try {
			conn = DBconnection.connect();
			//text="ganeshjadhav359@gmail.com";
			String sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, i.issueddate,u.email from usertable u,issuedbooks i" + 			
				",booktable b where u.userid=i.uid and u.userid=" +id +" and b.bid=i.bookid";
				System.out.println(sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Details d=new Details();
					d.setBname(rs.getString(1));
					d.setCategory(rs.getString(2));
					d.setIsbnNumber(rs.getString(3));
					d.setAuthorName(rs.getString(4));
					d.setIssuedDate(rs.getString(5));
					d.setUname(rs.getString(6));
					String issue=rs.getString(5);
					//--------------------------------------date conversion to show return date here---------------------------------
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				        Date d1 = format.parse(issue);
				        long time = d1.getTime();
				        System.out.println("Date represented is "+ d1);
				        Date d2 = new Date(time+(7*24*3600*1000));
						//System.out.println("Date represented is "+ d2 );
						String Rdate=format.format(d2);
			        //--------------------------------------date conversion to show return date ended here---------------------------------   

					d.setReturneDate(Rdate);
					Ubooks.add(d);
				}
				//ps.close();
				//return Ubooks;
		//text="ganeshjadhav359@gmail.com";
		sql = "select b.Bname,b.category,b.isbnnumber,b.authorname, r.issueddate,r.returneddate,u.email from usertable u,returnedbooks r" + 			
		",booktable b where u.userid=r.uid and u.userid=" +id+ " and b.bid=r.bookid";
		System.out.println(sql);
		 ps = conn.prepareStatement(sql);
		 rs = ps.executeQuery();

		while (rs.next()) {
			Details d=new Details();
			d.setBname(rs.getString(1));
			d.setCategory(rs.getString(2));
			d.setIsbnNumber(rs.getString(3));
			d.setAuthorName(rs.getString(4));
			d.setIssuedDate(rs.getString(5));
			d.setReturneDate(rs.getString(6));
			d.setUname(rs.getString(7));
			Ubooks.add(d);
		}

	
		//ps.close();
		return Ubooks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
			
	
	}
	
	
	
	
	
}

