package com.ys.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.test.context.junit4.SpringRunner;

import com.ys.entity.Contact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

	@Autowired
	private ContactRepository contactRepository;

	private List<Contact> contacts;

	@Before
	public void setup() throws Exception {
		contacts = readEntitiesFromFile();
	}

	@SuppressWarnings("unchecked")
	private List<Contact> readEntitiesFromFile() throws Exception {
		return (List<Contact>) new Jackson2ResourceReader().readFrom(new ClassPathResource("ContactEntities.json"),
				getClass().getClassLoader());
	}

	@Test
	public void testSave() {
		Contact contact = contacts.get(0);
		Contact savedContact = contactRepository.save(contact);
		assertThat(savedContact).isEqualTo(contact);
	}

	@Test
	public void testFindContactByContactId() {

	}

	@Test
	public void testFindContactById() {
		Contact contact = contacts.get(0);
		contactRepository.save(contact);
		Contact foundContact = contactRepository.findContactById((long) 3);
		assertThat(foundContact).isEqualTo(contact);
	}

	@Test
	public void testFindContactByFirstName() {
		Contact contact = contacts.get(0);
		contactRepository.save(contact);
		List<Contact> foundContact = contactRepository.findContactByFirstName("Bruce");
		assertThat(foundContact.get(0)).isEqualTo(contact);
	}

	@Test
	public void testFindContactByLastName() {
		Contact contact = contacts.get(0);
		contactRepository.save(contact);
		List<Contact> foundContact = contactRepository.findContactByLastName("Wayne");
		assertThat(foundContact.get(0)).isEqualTo(contact);
	}

}
