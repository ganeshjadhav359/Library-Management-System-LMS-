package controller;

import java.io.Serializable;

public class BookDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int					Bid;
	private String				isbnNumber;
	private String				Bname;
	private String				AuthorName;
	private int 				AvailableQuantity;
	private int 				IssuedQuantity;
	private String 				Description;
	private String 				ImgPath;
	private String 				Category;
	public BookDetails(int int1, String string, String string2, String string3, int i, int j,
			String string4, String string5, String string6) {
		
		// TODO Auto-generated constructor stub
		
		super();
		this.Bid = int1;
		this.isbnNumber = string;
		this.Bname= string2;
		this.AuthorName= string3;
		this.AvailableQuantity=i;
		this.IssuedQuantity=j;
		this.Description=string4;
		this.ImgPath=string5;
		this.Category=string6;
	}
	public String getIsbnNumber() {
		return isbnNumber;
	}
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}
	public int getBid() {
		return Bid;
	}
	public void setBid(int bid) {
		Bid = bid;
	}
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public String getAuthorName() {
		return AuthorName;
	}
	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}
	public int getAvailableQuantity() {
		return AvailableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		AvailableQuantity = availableQuantity;
	}
	public int getIssuedQuantity() {
		return IssuedQuantity;
	}
	public void setIssuedQuantity(int issuedQuantity) {
		IssuedQuantity = issuedQuantity;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getImgPath() {
		return ImgPath;
	}
	public void setImgPath(String imgPath) {
		ImgPath = imgPath;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}

}
