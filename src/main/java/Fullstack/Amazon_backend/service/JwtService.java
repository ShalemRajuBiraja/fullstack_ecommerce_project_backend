package Fullstack.Amazon_backend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import Fullstack.Amazon_backend.entity.UserTableEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	// 1. Import secret key and create security key
	// 2. Generate JWT token by using user details and security key
	
	@Value("${jwt.secret_key}")
	private String jwtsecretKey;
	
	public Key generateSecurityKey() {
		return Keys.hmacShaKeyFor(jwtsecretKey.getBytes());
	}
	
	
	
    public String generateJwtToken(UserTableEntity userData) {
    	
    	Date tokenGeneratedTimeDate = new Date();
    	Date tokenExpirationDate = new Date(tokenGeneratedTimeDate.getTime() + 24 * 60 * 60 * 1000);
    	
    	Map<String, Object> tokenDataMap = new HashMap<String, Object>();
    	tokenDataMap.put("userId", userData.getUserId());
    	tokenDataMap.put("userName", userData.getFullName());
    	tokenDataMap.put("email", userData.getEmail());
    	tokenDataMap.put( "mobileNumber", userData.getMobileNumber());
    	tokenDataMap.put("role", userData.getRole());
    	
    	//jwt key creation
    	String jwtTokenString = Jwts.builder()
    	.claims().add(tokenDataMap).and()
    	.subject(userData.getEmail())
    	.issuedAt(tokenGeneratedTimeDate)
    	.expiration(tokenExpirationDate)
    	.signWith(generateSecurityKey())
    	.compact();
    	
    	return jwtTokenString;
    			
    	
    	
    	
    }

}
