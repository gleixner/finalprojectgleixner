package edu.uw.course3demo.rest;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.service.ContainerService;


@Component
@Path("/")
public class ContainerResource {

	@Autowired
	ContainerService containerService;
	
	private Validator validator;
	
	public ContainerResource() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@GET
	@Path("container/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Container getContainer(@PathParam("id") Long id) {
		return containerService.getByIdEager(id);
	}
	
	@GET
	@Path("/containers")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Container> getAllContainers() {
		return containerService.listEager(0, 50);
	}
	
	@POST
	@Path("/container")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response saveContainer( Container container ) {
		Set<ConstraintViolation<Container>> violations = validator.validate(container);
		if(violations.size() > 0 ) 
			return Response.status(400).build();
		Long id = containerService.save(container);
		return Response.created(UriBuilder.fromPath("{arg1}").build(id)).build();
	}
	
	@PUT
	@Path("container/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response editProduct(@PathParam("id") Long id, Container container ) {
		container.setId(id);
		Set<ConstraintViolation<Container>> violations = validator.validate(container);
		if(violations.size() > 0 ) 
			return Response.status(400).build();
		containerService.save(container);
		return Response.ok().build();
	}
}
