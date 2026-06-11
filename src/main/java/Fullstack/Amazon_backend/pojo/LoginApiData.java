package Fullstack.Amazon_backend.pojo;

import Fullstack.Amazon_backend.constants.GenericeConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginApiData {
	
	@NotNull(message = "Email is required")
	@Pattern(regexp = GenericeConstants.EMAIL_REGEX_STRING, message = "Email is invalid")
	private String email;
	
	@NotNull(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
	

}
