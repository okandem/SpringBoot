package org.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.test.entity.Book;
import org.test.repository.BookRepository;

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
	@LocalServerPort
	private int port;


	private MockMvc mockMvc;
	private TestRestTemplate restTemplate = new TestRestTemplate();

@Before
public void setupMockMvc(){
	mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
}


	@Test
	public void contextLoads() {

		assertEquals(1,repository.count());
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
