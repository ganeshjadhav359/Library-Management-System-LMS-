package service;
import service.StoreController;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/GetService")
public class GetService {
	@GET
	@Produces("application/json")
	@Path("{name}")
	public String getData(@PathParam("name") String name) {
		if(name.equals("issued"))
		{
			System.out.println("here also i am");
			return new StoreController().IssuedBooks();
		}
		else if(name.equals("returned"))
		{
			return new StoreController().retrunBook();
		}
		else
		{
			return "{retruned: wrong input}";
		}	
		
	}
/*
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello World RESTful Jersey"
				+ "</hello>";
	}

	@GET
	@Produces()
	@Path("{name}")
	public String sayHtmlHello(@PathParam("name") String name) {
		return "<html> " + "<title>" + "Hello World RESTful Jersey"
				+ "</title>" + "<body><h1>"+name+ "Hello World RESTful Jersey"
				+ "</body></h1>" + "</html> ";
	}
*/	
}





