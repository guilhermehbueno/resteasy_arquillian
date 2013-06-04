package br.com.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/message")
public class MessageRestService implements MessageRestServiceInterface{
	
	

	public MessageRestService() {
		super();
		System.out.println("Construindo MessageRestService");
	}

	@GET
	@Path("/{param}")
	public String printMessage(@PathParam("param") String msg) {
		String result = "Restful example : " + msg;
		System.out.println(result);
		return result;
	}
}
