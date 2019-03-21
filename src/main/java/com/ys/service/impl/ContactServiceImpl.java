package com.ys.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.ys.entity.Contact;
import com.ys.model.ContactModel;
import com.ys.repository.ContactRepository;
import com.ys.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private DozerBeanMapper dozerMapper;

	@Override
	public List<ContactModel> getAllContacts() {
		return convert(this.contactRepository.findAll());
	}

	@Override
	public ContactModel getContactById(long id) {

		Contact contact = this.contactRepository.findContactById(id);
		if (contact != null) {
			return convert(contact);
		} else {
			return null;
		}
	}

	@Override
	public List<ContactModel> getContactByName(String firstName, String lastName) {

		if (firstName == null && lastName == null) {
			return convert(this.contactRepository.findAll());
		}

		List<Contact> contactListByFirstName = this.contactRepository.findContactByFirstName(firstName);

		Set<Contact> targetSet = new HashSet<>(contactListByFirstName);

		List<Contact> contactListByLastName = this.contactRepository.findContactByLastName(lastName);

		targetSet.addAll(contactListByLastName);

		List<Contact> contactList = new ArrayList<>(targetSet);

		return convert(contactList);
	}

	@Override
	public ContactModel createContact(ContactModel contactModel) {
		Contact contact = convertModel(contactModel);
		return convert(this.contactRepository.save(contact));
	}

	@Override
	public ContactModel updateContact(ContactModel contactModel) {
		Contact contact = this.contactRepository.findContactById(contactModel.getId());

		if (contact != null) {
			contact = convertModel(contactModel);
			return convert(this.contactRepository.save(contact));
		} else {
			return null;
		}
	}

	@Override
	public void deleteContact(long id) {
		this.contactRepository.deleteById(id);
	}

	private List<ContactModel> convert(List<Contact> contactList) {
		return contactList.stream().map(s -> dozerMapper.map(s, ContactModel.class)).collect(Collectors.toList());
	}

	private ContactModel convert(Contact contact) {
		return dozerMapper.map(contact, ContactModel.class);
	}

	private Contact convertModel(ContactModel contactModel) {
		return dozerMapper.map(contactModel, Contact.class);
	}

	@Bean
	public DozerBeanMapper dozerMapper() {
		return new DozerBeanMapper();
	}

}
