package com.expeditors.training.course3demo.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.expeditors.training.course3demo.config.TestConfig;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.service.ContainerService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class })
public class ContainerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ContainerService containerService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(containerService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testShowContainers() throws Exception {
		List<Container> result = new ArrayList<>();
		result.add( new Container( "Bob", new Double(1), "jfk", "ams", Status.ARRIVED ) );
		result.add( new Container( "Sue", new Double(2), "jfk", "ams", Status.READY) );
		result.add( new Container( "Jim", new Double(3), "jfk", "ams", Status.TRANSIT) );

		Mockito.when(containerService.getAll()).thenReturn(result);

		mockMvc.perform(get("/container/list.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/view/showContainers.jsp"))
				.andExpect(model().attributeExists("containers"))
				.andExpect(model().attribute("containers", hasSize(3) ) )
				.andExpect(model().attribute("containers", hasItem(
						allOf(
								hasProperty("name", is("Bob" ) ),
								hasProperty("capacity", is( 1.0 ) ),
								hasProperty("location", is("jfk") ),
								hasProperty("destination", is("ams") ),
								hasProperty("status", is(Status.ARRIVED) )
							)						
				)))
				.andExpect(model().attribute("containers", hasItem(
						allOf(
								hasProperty("name", is("Sue" ) ),
								hasProperty("capacity", is( new BigDecimal(2) ) ),
								hasProperty("location", is("jfk") ),
								hasProperty("destination", is("ams") ),
								hasProperty("status", is(Status.READY) )
							)						
				)))
				.andExpect(model().attribute("containers", hasItem(
						allOf(
								hasProperty("name", is("Jim" ) ),
								hasProperty("capacity", is( new BigDecimal(3) ) ),
								hasProperty("location", is("jfk") ),
								hasProperty("destination", is("ams") ),
								hasProperty("status", is(Status.TRANSIT) )
							)						
				)));

	}
	
	@Test
	public void testShowAddContainers() throws Exception {
		mockMvc.perform(get("/container/add.html") )
			.andExpect( status().isOk() )
			.andExpect( forwardedUrl("/WEB-INF/view/addContainer.jsp") )
			.andExpect( model().attributeExists("container") )
			.andExpect(model().attribute("container", hasProperty("name", equalTo( null ) )))
			.andExpect(model().attribute("container", hasProperty("capacity", equalTo( null ) )))
			.andExpect(model().attribute("container", hasProperty("location", equalTo( null ) )))
			.andExpect(model().attribute("container", hasProperty("destination", equalTo( null ) )))
			.andExpect(model().attribute("container", hasProperty("status", equalTo( null ) )));
	}
	
	@Test
	public void testAddContainerSuccess() throws Exception {
		mockMvc.perform( post("/container/add.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "bab")
				.param("capacity", "3")
				.param("location", "SFK")
				.param("destination", "NSY")
				.param("status", "READY")
				)
				.andExpect( status().isOk() )
				.andExpect( forwardedUrl("/WEB-INF/view/showContainer.jsp") )
				.andExpect( model().attributeExists("container") )
				.andExpect(model().attribute("container", hasProperty("name", equalTo( "bab" ) )))
				.andExpect(model().attribute("container", hasProperty("capacity", equalTo( 3.0 ) )))
				.andExpect(model().attribute("container", hasProperty("location", equalTo( "SFK" ) )))
				.andExpect(model().attribute("container", hasProperty("destination", equalTo( "NSY" ) )))
				.andExpect(model().attribute("container", hasProperty("status", equalTo( Status.READY ) )));
	}
	
	@Test
	public void testAddContainerNameError() throws Exception {
		mockMvc.perform( post("/container/add.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "")
				.param("capacity", "3")
				.param("location", "SFK")
				.param("destination", "")
				.param("status", "READY")
				)
				.andExpect( status().isOk() )
				.andExpect( forwardedUrl("/WEB-INF/view/addContainer.jsp") )
				.andExpect( model().attributeExists("container") )
				.andExpect(model().attributeHasFieldErrors("container", "name") )
				.andExpect(model().attribute("container", hasProperty("capacity", equalTo( 3.0 ) )))
				.andExpect(model().attribute("container", hasProperty("location", equalTo( "SFK" ) )))
				.andExpect(model().attribute("container", hasProperty("destination", equalTo( "" ) )))
				.andExpect(model().attribute("container", hasProperty("status", equalTo( Status.READY ) )));
	}
	
	@Test
	public void testAddContainerLocationError() throws Exception {
		mockMvc.perform( post("/container/add.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "bab")
				.param("capacity", "3")
				.param("location", "sfk")
				.param("destination", "")
				.param("status", "READY")
				)
				.andExpect( status().isOk() )
				.andExpect( forwardedUrl("/WEB-INF/view/addContainer.jsp") )
				.andExpect( model().attributeExists("container") )
				.andExpect(model().attribute("container", hasProperty("name", equalTo( "bab" ) )))
				.andExpect(model().attribute("container", hasProperty("capacity", equalTo( 3.0 ) )))
				.andExpect(model().attributeHasFieldErrors("container", "location") )
				.andExpect(model().attribute("container", hasProperty("destination", equalTo( "" ) )))
				.andExpect(model().attribute("container", hasProperty("status", equalTo( Status.READY ) )));
	}
	
	@Test
	public void testAddContainerStatusError() throws Exception {
		mockMvc.perform( post("/container/add.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "bab")
				.param("capacity", "3")
				.param("location", "SFK")
				.param("destination", "")
				.param("status", "steve")
				)
				.andExpect( status().isOk() )
				.andExpect( forwardedUrl("/WEB-INF/view/addContainer.jsp") )
				.andExpect( model().attributeExists("container") )
				.andExpect(model().attribute("container", hasProperty("name", equalTo( "bab" ) )))
				.andExpect(model().attribute("container", hasProperty("capacity", equalTo( 3.0 ) )))
				.andExpect(model().attribute("container", hasProperty("location", equalTo( "SFK" ) )))
				.andExpect(model().attribute("container", hasProperty("destination", equalTo( "" ) )))
				.andExpect(model().attributeHasFieldErrors("container", "status") );
	}
	
	@Test
	public void testAddContainerDestinationFailure() throws Exception {
		mockMvc.perform( post("/container/add.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "bab")
				.param("capacity", "3")
				.param("location", "SFK")
				.param("destination", "bab")
				.param("status", "READY")
				)
				.andExpect( status().isOk() )
				.andExpect( forwardedUrl("/WEB-INF/view/addContainer.jsp") )
				.andExpect( model().attributeExists("container") )
				.andExpect(model().attribute("container", hasProperty("name", equalTo( "bab" ) )))
				.andExpect(model().attribute("container", hasProperty("capacity", equalTo( 3.0 ) )))
				.andExpect(model().attribute("container", hasProperty("location", equalTo( "SFK" ) )))
				.andExpect(model().attributeHasFieldErrors("container", "destination") )
				.andExpect(model().attribute("container", hasProperty("status", equalTo( Status.READY ) )));
	}
}