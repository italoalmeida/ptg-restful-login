package com.italoalmeida.ptg.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.italoalmeida.ptg.models.Phone;
import com.italoalmeida.ptg.models.User;

/**
 * @author Italo Almeida
 *
 */
public class JwtUser implements Serializable {

	private static final long serialVersionUID = -874071359709399690L;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<Phone> phones;
	
	private Date createdAt;
	
	private Date lastLogin;
	
	public JwtUser() {
	}

	public JwtUser(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.phones = user.getPhones();
		this.createdAt = user.getCreatedAt();
		this.lastLogin = user.getLastLogin();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
