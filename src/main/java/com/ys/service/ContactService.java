package com.ys.service;

import java.util.List;

import com.ys.model.ContactModel;

public interface ContactService {

	List<ContactModel> getAllContacts();
	
	ContactModel getContactById(long id);

	List<ContactModel> getContactByName(String firstName, String lastName);

	ContactModel createContact(ContactModel contactModel);

	ContactModel updateContact(ContactModel contactModel);

	void deleteContact(long id);

	

}
