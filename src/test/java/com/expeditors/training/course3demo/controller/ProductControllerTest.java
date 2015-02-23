package com.expeditors.training.course3demo.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.expeditors.training.course3demo.config.TestConfig;
import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestConfig.class })
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ProductService productService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		// We have to reset our mock between tests because the mock objects
		// are managed by the Spring container. If we would not reset them,
		// stubbing and verified behavior would "leak" from one test to another.
		Mockito.reset(productService);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testGetProduct() throws Exception {
		Product first = new Product("foo", "Lorem ipsum", 1.99);
		Product second = new Product("two", "Lorem ipsum 2", .99);

		Mockito.when(productService.getProduct(1L)).thenReturn(first);
		Mockito.when(productService.getProduct(2L)).thenReturn(second);

		mockMvc.perform(get("/product/show.html?product_id=1"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/view/showProduct.jsp"))
				.andExpect(model().attributeExists("product"))
				.andExpect(model().attribute("product", hasProperty("name", equalTo("foo"))));

		mockMvc.perform(get("/product/show.html?product_id=2"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/view/showProduct.jsp"))
				.andExpect(model().attributeExists("product"))
				.andExpect(model().attribute("product", hasProperty("name", equalTo("two"))));

	}
}