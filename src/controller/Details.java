package controller;

import java.io.Serializable;

public class Details implements Serializable{
	private String				isbnNumber;
	private String				Bname;
	private String				AuthorName;
	private int 				AvailableQuantity;
	private int 				IssuedQuantity;
	private String 				ImgPath;
	private String 				Category;
	private String				issuedDate;
	private String 				returneDate;
	private String 				uname;
	public String getIsbnNumber() {
		return isbnNumber;
	}
	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
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
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getReturneDate() {
		return returneDate;
	}
	public void setReturneDate(String returneDate) {
		this.returneDate = returneDate;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
}
