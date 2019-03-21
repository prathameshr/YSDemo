package com.ys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ys.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	public Contact findContactById(long id);

	public List<Contact> findContactByFirstName(String firstName);

	public List<Contact> findContactByLastName(String lastName);

}
