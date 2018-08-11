package controller;

public class Product {
private int pid,quantity;
private String pname,feedback;

 public Product(int pid,int quantity,String pname,String feedback)
 {
	 
	this.setPid(pid);
	this.setQuantity(quantity);
	this.setPname(pname);
	this.setFeedback(feedback);
 }

public String getPname() {
	return pname;
}

public void setPname(String pname) {
	this.pname = pname;
}

public String getFeedback() {
	return feedback;
}

public void setFeedback(String feedback) {
	this.feedback = feedback;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public int getPid() {
	return pid;
}

public void setPid(int pid) {
	this.pid = pid;
}

}
