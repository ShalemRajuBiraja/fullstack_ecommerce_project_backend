package Fullstack.Amazon_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Fullstack.Amazon_backend.payload.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── 1. Validation Errors (@NotBlank, @Email, @Size etc.) ──
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Validation Failed");
        response.put("errors", fieldErrors);
        

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleException(Exception ex){
		
		ApiResponse<String> response = new ApiResponse<>(false,  ex.getMessage(),null);
		
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	
	}
	
}

