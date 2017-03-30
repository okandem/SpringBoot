package org.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.test.entity.Book;
import org.test.repository.BookRepository;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get	;

import static		org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static		org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static		org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"management.port=0", "management.context-path=/admin" })

public class DemoApplicationTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private BookRepository repository;
	@Autowired
	private DataSource ds;
	@LocalServerPort
	private int port;


	private MockMvc mockMvc;
	private TestRestTemplate restTemplate = new TestRestTemplate();
	private static boolean loadDataFixtures =true;

@Before
public void setupMockMvc(){
	mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
}

@Before
public void setLoadDataFixtures(){
	if(loadDataFixtures){
		//bütün testlerden önce çalışmaması için. 1 kere çalışması için.
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator(context.getResource("classpath:/test-data.sql"));
		DatabasePopulatorUtils.execute(populator,ds);
		loadDataFixtures=false;
	}
}


	@Test
	public void contextLoads() {

		assertEquals(4,repository.count());
	}

	@Test
	public void webappBookIsbnApi(){
		Book book = restTemplate.getForObject("http://localhost:"+port+"/books/978-1-78528-415-1",Book.class);
		assertNotNull(book);
		assertEquals("Packt",book.getPublisher().getName());
	}

	@Test
	public void webappPublisherApi() throws Exception {
		mockMvc.perform(get("/publishers/1"))
				.andExpect(status().is(200))
				.andExpect(content().contentType(MediaType.parseMediaType("application/hal+json;charset=UTF-8")))
				.andExpect(content().string(containsString("Packt")))
				.andExpect(jsonPath	("$.name").value("Packt"));

	}

}
