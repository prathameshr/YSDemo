package com.ys.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ys.entity.Contact;
import com.ys.model.ContactModel;
import com.ys.repository.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
public class ContactServiceTest {

	@Autowired
	private ContactService contactService;

	@MockBean
	private ContactRepository contactRepository;

	private List<Contact> contactEntities;

	private List<ContactModel> contactModels;

	@Before
	public void setup() throws Exception {
		contactEntities = readContactEntitiesFromFile();
		contactModels = readContactModelsFromFile();
	}

	@SuppressWarnings("unchecked")
	private List<Contact> readContactEntitiesFromFile() throws Exception {
		return (List<Contact>) new Jackson2ResourceReader().readFrom(new ClassPathResource("ContactEntities.json"),
				getClass().getClassLoader());
	}

	@SuppressWarnings("unchecked")
	private List<ContactModel> readContactModelsFromFile() throws Exception {
		return (List<ContactModel>) new Jackson2ResourceReader().readFrom(new ClassPathResource("ContactModels.json"),
				getClass().getClassLoader());
	}

	@Test
	public void testGetAllContacts() {
		BDDMockito.given(contactRepository.findAll()).willReturn(contactEntities);
		assertNotNull(contactService.getAllContacts());
	}

	@Test
	public void testGetContactById() {
		BDDMockito.given(contactRepository.findContactById((long) 4)).willReturn(contactEntities.get(1));
		assertThat(contactService.getContactById((long) 4).getId()).isEqualTo(4);
	}

	@Test
	public void testGetContactByName() {
		BDDMockito.given(contactRepository.findContactByFirstName("Banner")).willReturn(contactEntities);
		assertThat(contactService.getContactByName("Banner", null).get(0).getId()).isEqualTo(4);
	}

	@Test
	public void testCreateContact() {
		BDDMockito.given(contactRepository.save(BDDMockito.any())).willReturn(contactEntities.get(0));
		ContactModel contact = contactModels.get(0);

		ContactModel contactCreated = contactService.createContact(contact);
		assertThat(contactCreated.getId()).isEqualTo(3);
	}

	@Test
	public void testUpdateContact() {
		BDDMockito.given(contactRepository.findContactById(3)).willReturn(contactEntities.get(0));

		BDDMockito.given(contactRepository.save(BDDMockito.any())).willReturn(contactEntities.get(2));

		ContactModel sampleModel = contactModels.get(0);

		sampleModel.setFirstName("Tony");

		ContactModel updatedContact = contactService.updateContact(sampleModel);

		assertNotNull(updatedContact);

		assertThat(updatedContact.getFirstName()).isEqualTo("Tony");

	}

}
