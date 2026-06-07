package Fullstack.Amazon_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Fullstack.Amazon_backend.payload.ApiResponse;
import Fullstack.Amazon_backend.pojo.LoginApiData;
import Fullstack.Amazon_backend.pojo.SignupApiData;
import Fullstack.Amazon_backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signup")
	public Object signup( @Valid @RequestBody SignupApiData signupApiData) throws Exception {
		
				authService.signupFunction(signupApiData);
		
	ApiResponse<Object> response = new ApiResponse<>(true, "Signup successful", null);			
				
		return response;
	}
	
	
	public String login( @Valid LoginApiData loginApiData) {
		
		
		return "Login successful";
	}

}
