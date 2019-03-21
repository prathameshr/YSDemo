package com.ys.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Contact attributes")
public class ContactModel {

	@ApiModelProperty(value = "Contact id")
	@JsonProperty(value = "id")
	private long id;

	@ApiModelProperty(value = "First Name", required = true)
	private String firstName;

	@ApiModelProperty(value = "Last Name", required = true)
	private String lastName;

	@ApiModelProperty(value = "Phone Number", required = true)
	private int phoneNumber;

	@ApiModelProperty(value = "Address", required = true)
	private String address;

	@ApiModelProperty(value = "Email Address", required = true)
	private String emailAddress;

}
