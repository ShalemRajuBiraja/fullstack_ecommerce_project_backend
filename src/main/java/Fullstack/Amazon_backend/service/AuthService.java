package Fullstack.Amazon_backend.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Fullstack.Amazon_backend.constants.AuthConstants;
import Fullstack.Amazon_backend.entity.UserTableEntity;
import Fullstack.Amazon_backend.pojo.LoginApiData;
import Fullstack.Amazon_backend.pojo.SignupApiData;
import Fullstack.Amazon_backend.repository.AuthRepository;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;
    
    @Autowired
    JwtService jwtService;

// 1. SIGN UP FUNCTION   
    public void signupFunction(SignupApiData signupApiData) throws Exception {

        // ── 1. Check Email already exists ──
        Optional<UserTableEntity> existingEmail = authRepository.findByEmail(signupApiData.getEmail());
        if (existingEmail.isPresent()) {
            throw new Exception("Email already registered!");
        }
        
        // ── 2. Check Mobile number already exists ──
        Optional<UserTableEntity> existingMobile = authRepository.findByMobileNumber(signupApiData.getMobileNumber());
        if (existingMobile.isPresent()) {
			throw new Exception("Mobile number already registered!");
		}

        // ── 3. Map POJO → Entity ──
        UserTableEntity newUser = new UserTableEntity();
        newUser.setFullName(signupApiData.getFullName());
        newUser.setEmail(signupApiData.getEmail());
        newUser.setMobileNumber(signupApiData.getMobileNumber());
        newUser.setPassword(signupApiData.getPassword());
        newUser.setRole("user");
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        // ── 4. Save to DB ──
        authRepository.save(newUser);

        
    }
    
// 2. LOGINING INTO WEBSITE 
    public Map<String, Object> LoginFunction(LoginApiData loginApiData) throws Exception {
    	
    	
    	Optional<UserTableEntity> userDataOptional= authRepository.findByEmail(loginApiData.getEmail());
    	// ── 1. Check if email exists ──
    		if(userDataOptional.isEmpty()) {
    			throw new Exception(AuthConstants.EXCEPTION_EMAIL_NOT_FOUND_STRING);
    		}
    		
    		UserTableEntity userData = userDataOptional.get();
    	// 2. Check password right or wrong	
    		
    		if(!userData.getPassword().equals(loginApiData.getPassword())) {
				throw new Exception(AuthConstants.EXCEPTION_PASSWORD_INCORRECT_STRING);
			}
    	// 3. If both are correct, Generate JWT token, login successful, you can return user data or token as needed
			
    		String jwtTokenString = jwtService.generateJwtToken(userData);
    		
    		Map<String, Object> authResponse = new HashMap<>();
    		authResponse.put("userData", userData);
    		authResponse.put("token", jwtTokenString);
    		
    		return authResponse;
    		
		
    		
    }
    
}