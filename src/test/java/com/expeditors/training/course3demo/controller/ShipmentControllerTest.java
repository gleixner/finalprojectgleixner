package com.expeditors.training.course3demo.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
	public void testAddDuplicateContainer() throws Exception {
		Shipment s = new Shipment();
		
		Mockito.when( shipmentService.addShipment(s)).thenReturn( false );
		
		mockMvc.perform( post("/shipment/add.html")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED )
			.param("name", "bab")
			.param("origin", "AAA")
			.param("destination", "BBB")
			.param("volume", "5") 
			)
			.andExpect( status().isOk() )
			.andExpect( forwardedUrl("/WEB-INF/view/addShipment.jsp") )
			.andExpect( model().attributeExists("error") )
			.andExpect( model().attributeExists("shipment"))
			.andExpect( model().attribute("error", equalTo( "true" ) ) )
			.andExpect( model().attribute("shipment", hasProperty("name", equalTo( "bab" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("origin", equalTo( "AAA" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("destination", equalTo( "BBB" ) ) ) )
			.andExpect( model().attribute("shipment", hasProperty("volume", equalTo(new BigDecimal(5)) ) ) );
	}
}
