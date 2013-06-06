package br.com.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/message")
public interface MessageRestServiceInterface {
	
	@GET
	@Path("/{param}")
	public String printMessage(@PathParam("param") String msg);

}
