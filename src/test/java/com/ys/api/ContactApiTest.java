package com.ys.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ys.service.ContactService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public class ContactApiTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private DozerBeanMapper dozerMapper;

	@MockBean
	private ContactService contactService;

	@Test
	public void testGetById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/contact/3").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("")));
	}

	@Test
	public void testGetByName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/contact").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
	}

	@Test
	public void testCreate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/v1/contact").contentType(MediaType.APPLICATION_JSON)
				.content("{" + "  \"firstName\": \"Bruce\"," + "  \"lastName\": \"Wayne\","
						+ "  \"phoneNumber\": \"909022223\"," + "  \"address\": \"Dublin\","
						+ "  \"emailAddress\": \"sample@gmail.com\"" + "}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/v1/contact").contentType(MediaType.APPLICATION_JSON)
				.content("{" + "  \"id\": 3," + "  \"firstName\": \"Bruce\"," + "  \"lastName\": \"Wayne\","
						+ "  \"phoneNumber\": \"909022223\"," + "  \"address\": \"Dublin\","
						+ "  \"emailAddress\": \"sample@gmail.com\"" + "}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/v1/contact/3").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("Contact 3 is deleted!")));
	}

}
