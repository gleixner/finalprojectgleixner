package com.expeditors.training.course3demo.service;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

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
import com.expeditors.training.course3demo.model.Product;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServiceTestConfig.class})
@TestExecutionListeners ( {DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class
})
public class ProductServiceTest {
	@Autowired
	public ProductService productService;
	
	@Test
	@DatabaseSetup("ProductData.xml")
	public void testGetProductById() {
		Product p = productService.getProduct(1l);
		assertThat(p, hasProperty("name", is("L")));
	}

}