package com.italoalmeida.ptg.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Italo Almeida
 *
 */
@Entity
@Table(name = "PHONE", uniqueConstraints = {@UniqueConstraint(columnNames = {"number", "areaCode", "countryCode"})})
public class Phone implements Serializable {

	private static final long serialVersionUID = -4353061489840065831L;
	
	@Id
	private Integer number;
	
	@Id
	private Integer areaCode;
	
	@Id
	private String countryCode;
	
	public Phone() {
		super();
	}

	public Phone(Integer number, Integer areaCode, String countryCode) {
		super();
		this.number = number;
		this.areaCode = areaCode;
		this.countryCode = countryCode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
