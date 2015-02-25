package com.expeditors.training.course3demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.expeditors.training.course3demo.config.ServiceTestConfig;
import com.expeditors.training.course3demo.model.Shipment;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceTestConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
						   DirtiesContextTestExecutionListener.class,
						   TransactionalTestExecutionListener.class,
						   DbUnitTestExecutionListener.class})
public class ShipmentServiceTest {

	@Autowired
	public ShipmentService shipmentService;
	
	@Test
	@DatabaseSetup("ShipmentData.xml")
	public void testGetShipmentById() {
		Shipment s = shipmentService.getById(1L);
		assertThat(s, hasProperty("id", is(1L)));
		assertThat(s, hasProperty("name", is("a")));
		assertThat(s, hasProperty("origin", is("BBB")));
		assertThat(s, hasProperty("destination", is("CCC")));
		assertThat(s, hasProperty("volume", is(5.0)));
	}
	
}
