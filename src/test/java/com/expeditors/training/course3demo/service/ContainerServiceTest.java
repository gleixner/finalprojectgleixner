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
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServiceTestConfig.class})
@TestExecutionListeners ( {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class
})
public class ContainerServiceTest {

	@Autowired
	public ContainerService containerService;
	
	@Test
	@DatabaseSetup("ContainerData.xml")
	public void testGetContainerById() {
		Container c = containerService.getById(1L);
		assertThat(c, hasProperty("id", is(1L)));
		assertThat(c, hasProperty("name", is("bab")));
		assertThat(c, hasProperty("capacity", is(5.0)));
		assertThat(c, hasProperty("location", is("AAA")));
		assertThat(c, hasProperty("destination", is("BBB")));
		assertThat(c, hasProperty("status", is(Status.READY)));
	}
	
}
