package service;

import java.io.Serializable;

public class Details implements Serializable{
	private String				isbnNumber;
	private String				Bname;
	private String				AuthorName;
	private int 				AvailableQuantity;
	private int 				IssuedQuantity;
	private String 				ImgPath;
	private String 				Category;
	private String 				email;
	private String				issuedDate;
	private String 				returnedDate;
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
	public String getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
