package br.inatel.dm110.hello.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
public interface InventoryService {

	@GET
	@Path("/product/names")
	@Produces(MediaType.APPLICATION_JSON)
	List<String> listAllProducts();

	@POST
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	void createNewProduct(ProductTO productTO);

}
