package com.italoalmeida.ptg.validators;

import static com.italoalmeida.ptg.validators.ValidatorUtil.isVaildString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.italoalmeida.ptg.exceptions.InvalidFieldsException;
import com.italoalmeida.ptg.exceptions.UnauthorizedException;
import com.italoalmeida.ptg.security.JwtTokenUtil;

/**
 * @author Italo Almeida
 *
 */
@Component
public class TokenValidator implements Validator<String> {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	public void validate(String model) throws Exception {
		Validator.super.validate(model);
		jwtTokenUtil.doValidateToken(model);
	}
	
	@Override
	public void validateRequiredFields(String model) throws Exception {
		if (model == null) throw new UnauthorizedException();
	}

	@Override
	public void validateFieldsValues(String model) throws Exception {
		if (!isVaildString(model)) throw new InvalidFieldsException();
	}

	@Override
	public void validateUniqueFields(String model) throws Exception {}

}
