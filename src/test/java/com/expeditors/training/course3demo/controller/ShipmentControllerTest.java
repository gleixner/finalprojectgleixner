package com.expeditors.training.course3demo.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.ShipmentService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class })
public class ShipmentControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ShipmentService shipmentService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		Mockito.reset(shipmentService);
		mockMvc = MockMvcBuilders.webAppContextSetup( webApplicationContext ).build();
	}
	
	@Test
	public void testAddShipment() throws Exception {
		Shipment s = new Shipment();
		
		Mockito.when( shipmentService.save(s)).thenReturn( 1L );
		
		mockMvc.perform( post("/shipment/add.html")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED )
			.param("name", "bab")
			.param("origin", "AAA")
			.param("destination", "BBB")
			.param("volume", "5") 
			)
			.andExpect( status().isMovedTemporarily() )
			.andExpect( redirectedUrl("/shipment/show.html?name=bab") )
//			.andExpect( model().attributeExists("error") )
			.andExpect( model().attributeExists("shipment"))
			.andExpect( model().attribute("shipment", hasProperty("name", equalTo( "bab" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("origin", equalTo( "AAA" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("destination", equalTo( "BBB" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("volume", is( 5.0 ) ) ) );
	}
}
