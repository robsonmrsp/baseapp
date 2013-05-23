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

import br.com.sints.app.json.JsonRole;
import br.com.sints.app.service.RoleService;

@Path("/crud/roles")
public class RoleResources {

	@Inject
	RoleService roleService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonRole> getAllEventTypes() {
		return roleService.allRoles();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventType(@PathParam("id") Long id) {
		return Response.ok().entity(roleService.getRole(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveEventType(JsonRole role) {
		return Response.ok().entity(roleService.save(role)).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateEventType(@PathParam("id") Long id, JsonRole role) {
		return Response.ok().entity(roleService.update(role)).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEventType(@PathParam("id") Long id) {
		return Response.ok().entity(roleService.delete(id)).build();
	}
}
