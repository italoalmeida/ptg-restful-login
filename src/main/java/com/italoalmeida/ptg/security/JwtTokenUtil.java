package com.italoalmeida.ptg.security;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.italoalmeida.ptg.exceptions.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

/**
 * @author Italo Almeida
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 7632214350465142062L;
	
	private Clock clock = DefaultClock.INSTANCE;
	
	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
	
	/**
	 * @param jwtUser
	 * @return String
	 */
	public String doGenerateToken(JwtUser jwtUser) {
		Date issuedDate = clock.now();
		Date expirationDate = defineExpirationDateFrom(issuedDate);
		
		jwtUser.setLast_login(issuedDate);
		
		String stringfiedJwtUser = stringfyJwtUser(jwtUser);

		String token = Jwts.builder()
				.setSubject(jwtUser.getEmail())
				.claim("user", stringfiedJwtUser)
				.setIssuedAt(issuedDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

		return token;
	}

	/**
	 * @param issuedDate
	 * @return Date
	 */
	private Date defineExpirationDateFrom(Date issuedDate) {
		return new Date(issuedDate.getTime() + expiration);
	}
	
	/**
	 * @param token
	 * @return JwtUser
	 */
	public JwtUser getUserFromToken(String token) {
		if (token.startsWith("Bearer ")) token = token.substring(7);
		String stringfiedJwtUser = getAllClaimsFromToken(token).get("user", String.class);
		return parserJwtUser(stringfiedJwtUser);
    }
	
	/**
	 * @param token
	 * @return Claims
	 */
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}
	
	/**
	 * @param jwtUser
	 * @return String
	 */
	private String stringfyJwtUser(JwtUser jwtUser) {
		String stringfiedJwtUser = "";
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			stringfiedJwtUser = mapper.writeValueAsString(jwtUser);
			
		} catch (Exception e) {
			e.getStackTrace();
		}

		return stringfiedJwtUser;
	}
	
	/**
	 * @param stringfiedJwtUser
	 * @return JwtUser
	 */
	private JwtUser parserJwtUser(String stringfiedJwtUser) {
		JwtUser jwtUser = null;
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			
			jwtUser = mapper.readValue(stringfiedJwtUser, JwtUser.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jwtUser;
	}
	
	/**
	 * @param token
	 * @throws UnauthorizedException
	 */
	public void doValidateToken(String token) throws UnauthorizedException {
		try {
			
			if (token.startsWith("Bearer ")) token = token.substring(7);
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
        } catch (ExpiredJwtException e) {
        	throw new UnauthorizedException("invalid session");
        } catch (Exception e) {
        	throw new UnauthorizedException();
		}
    }

}
