package Fullstack.Amazon_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Fullstack.Amazon_backend.constants.AuthConstants;
import Fullstack.Amazon_backend.payload.ApiResponse;
import Fullstack.Amazon_backend.pojo.LoginApiData;
import Fullstack.Amazon_backend.pojo.SignupApiData;
import Fullstack.Amazon_backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/auth/create-account")
	public Object signup( @Valid @RequestBody SignupApiData signupApiData) throws Exception {
		
				authService.signupFunction(signupApiData);
		
	ApiResponse<Object> response = new ApiResponse<>(true, "Signup successful", null);			
				
		return response;
	}
	
	
	
	@PostMapping("/auth/login")
	public  ApiResponse<Map<String, Object>> login ( @Valid @RequestBody LoginApiData loginApiData) throws Exception {
		System.out.println("Login API called with email: " + loginApiData);
		 Map<String, Object> authServiceResponseMap = authService.LoginFunction(loginApiData);
		 
		 ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>(true, AuthConstants.SUCCESS_LOGIN_STRING, authServiceResponseMap);
		 
		 return apiResponse;
		
	}

}
