package com.italoalmeida.ptg.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.italoalmeida.ptg.models.User;
import com.italoalmeida.ptg.repositories.UserRepository;
import com.italoalmeida.ptg.validators.UserValidator;

/**
 * @author Italo Almeida
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserValidator userValidator;
	
	@Override
	public User save(User user) throws Exception {
		
		userValidator.validate(user);
		
		this.encryptPassword(user);
		
		return userRepository.save(user);
	}

	private void encryptPassword(User user) {
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(encryptedPassword);
	}

}
