package com.italoalmeida.ptg.validators;

import static com.italoalmeida.ptg.validators.ValidatorUtil.isVaildString;
import static com.italoalmeida.ptg.validators.ValidatorUtil.isValidPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.italoalmeida.ptg.exceptions.InvalidCredentialsException;
import com.italoalmeida.ptg.exceptions.MissingFieldsException;
import com.italoalmeida.ptg.models.User;
import com.italoalmeida.ptg.repositories.UserRepository;
import com.italoalmeida.ptg.security.Credentials;

/**
 * @author Italo Almeida
 *
 */
@Component
public class CredentialsValidator implements Validator<Credentials> {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void validate(Credentials model) throws Exception {
		Validator.super.validate(model);
		this.validateRegisteredFields(model);
	}

	@Override
	public void validateRequiredFields(Credentials model) throws Exception {

		if (!isVaildString(model.getEmail())) throw new MissingFieldsException();
		
		if (!isVaildString(model.getPassword())) throw new MissingFieldsException();
	}

	@Override
	public void validateFieldsValues(Credentials model) throws Exception {}

	@Override
	public void validateUniqueFields(Credentials model) throws Exception {}
	
	public void validateRegisteredFields(Credentials model) throws Exception {
		
		User user = userRepository.findByEmail(model.getEmail());
		
		if (user == null) throw new InvalidCredentialsException();
		
		if (!isValidPassword(model.getPassword(), user.getPassword())) throw new InvalidCredentialsException(); 
	}

}
