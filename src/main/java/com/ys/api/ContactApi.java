package com.ys.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ys.model.ContactModel;
import com.ys.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/v1/contact", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Contact Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ContactApi {

	@Autowired
	private ContactService contactService;

	@ApiOperation(value = "Get Contact by Id")
	@GetMapping("/{id}")
	public ResponseEntity<ContactModel> getById(
			@ApiParam(value = "Contact Id", required = true) @PathVariable long id) {
		return ResponseEntity.ok(contactService.getContactById(id));
	}

	@ApiOperation(value = "Get All Contacts or Contact by First Name or LastName")
	@GetMapping
	public ResponseEntity<List<ContactModel>> getByName(
			@ApiParam(value = "First Name", required = false) @RequestParam(value = "firstname", required = false) String firstName,
			@ApiParam(value = "Last Name", required = false) @RequestParam(value = "lastname", required = false) String lastName) {
		return ResponseEntity.ok(contactService.getContactByName(firstName, lastName));
	}

	@ApiOperation(value = "Create a Contact")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Success", response = ContactModel.class),
		    @ApiResponse(code = 401, message = "Unauthorized"),
		    @ApiResponse(code = 403, message = "Forbidden"),
		    @ApiResponse(code = 404, message = "Not Found"),
		    @ApiResponse(code = 500, message = "Failure")})
	@PostMapping
	public ResponseEntity<ContactModel> create(
			@ApiParam(value = "Create Contact", required = true) @Valid @RequestBody ContactModel contactModel) {

		return ResponseEntity.ok(contactService.createContact(contactModel));
	}

	@ApiOperation(value = "Update a Contact")
	@PutMapping
	public ResponseEntity<ContactModel> update(
			@ApiParam(value = "Contact Data to update", required = true) @Valid @RequestBody ContactModel contactModel) {

		return ResponseEntity.ok(contactService.updateContact(contactModel));
	}

	@ApiOperation(value = "Delete a Contact")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(
			@ApiParam(value = "Contact id to delete", required = true) @PathVariable long id) {

		contactService.deleteContact(id);
		return ResponseEntity.ok(String.format("Contact %s is deleted!", id));
	}

}
