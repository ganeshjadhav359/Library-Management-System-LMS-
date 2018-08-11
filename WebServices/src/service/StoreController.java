package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dbManager.*;
public class StoreController {
//	List<BookDetails>					books	= new ArrayList<BookDetails>();

	private static Connection	conn;
	
		public String IssuedBooks()
		{
			List<Details>					Issuedbooks	= new ArrayList<Details>();
		try {
			conn = DBconnection.connect();
			//text="ganesh@gmail.com";
			String sql = "select b.isbnnumber,b.Bname,b.category,u.email,i.issueddate from usertable u,issuedbooks i" + 			
			",booktable b where u.userid=i.uid and b.bid=i.bookid";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(" after conn");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Details d=new Details();
				
				d.setIsbnNumber(rs.getString(1));
				d.setBname(rs.getString(2));
				d.setCategory(rs.getString(3));
				d.setEmail(rs.getString(4));
				d.setIssuedDate(rs.getString(5));
				Issuedbooks.add(d);
			}
			//ps.close();
			
			String json = new Gson().toJson(Issuedbooks);
			System.out.println(json);
			return json;
				

		} catch (SQLException e) {
			System.out.println("eception in issue ");
			e.printStackTrace();
		}
		
		return null;
		
	}
		public String retrunBook()
		{
			List<Details>					Issuedbooks	= new ArrayList<Details>();
		try {
			conn = DBconnection.connect();
			//text="ganesh@gmail.com";
			String sql = "select b.isbnnumber,b.Bname,b.category,u.email,r.issueddate,r.returneddate from usertable u,returnedbooks r" + 			
			",booktable b where u.userid=r.uid and b.bid=r.bookid";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Details d=new Details();
				
				d.setIsbnNumber(rs.getString(1));
				d.setBname(rs.getString(2));
				d.setCategory(rs.getString(3));
				d.setEmail(rs.getString(4));
				d.setIssuedDate(rs.getString(5));
				d.setReturnedDate(rs.getString(5));
				Issuedbooks.add(d);
			}
			//ps.closeoo();
			
			String json = new Gson().toJson(Issuedbooks);
			System.out.println(json);
			return json;
				

		} catch (SQLException e) {
			System.out.println("eception in return ");
			e.printStackTrace();
		}
		
		return null;
		
	}
}
