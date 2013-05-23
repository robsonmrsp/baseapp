package br.com.sints.app.rs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sints.app.json.JsonUser;
import br.com.sints.app.service.UserService;

@Path("/crud/users")
public class UserResources {

	@Inject
	UserService panelUserService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonUser> getAllUsers() {
		return panelUserService.allUsers();
	}

	@GET
	@Path("/loggedUser")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonUser getLogguedUser() {
		return panelUserService.getLoguedUser();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Long id) {
		return Response.ok().entity(panelUserService.getUser(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUser(JsonUser event) {
		return Response.ok().entity(panelUserService.save(event)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateUser(@PathParam("id") Long id, JsonUser event) {
		return Response.ok().entity(panelUserService.update(event)).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Long id) {
		return Response.ok().entity(panelUserService.delete(id)).build();
	}
}
