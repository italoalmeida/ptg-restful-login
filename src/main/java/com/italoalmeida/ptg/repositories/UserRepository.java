package com.italoalmeida.ptg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.italoalmeida.ptg.models.User;

/**
 * @author Italo Almeida
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

}
