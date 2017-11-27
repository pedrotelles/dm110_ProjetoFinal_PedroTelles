package br.inatel.dm110.hello.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public interface HelloService {

	@GET
	@Path("/say/{name}")
	@Produces(MediaType.TEXT_HTML)
	String sayHello(@PathParam("name") String name);

	@POST
	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	Result message(@FormParam("first") String first, @FormParam("last") String last);

}
