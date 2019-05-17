package com.italoalmeida.ptg.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author Italo Almeida
 *
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 3004049128350546151L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true)
	private Date lastLogin;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public User() {
		super();
	}

	public User(@NotNull String firstName, @NotNull String lastName,
			@NotNull String email, @NotNull String password,
			@NotNull List<Phone> phones) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phones = phones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
