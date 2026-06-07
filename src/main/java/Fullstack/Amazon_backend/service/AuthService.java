package Fullstack.Amazon_backend.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Fullstack.Amazon_backend.entity.UserTableEntity;
import Fullstack.Amazon_backend.pojo.SignupApiData;
import Fullstack.Amazon_backend.repository.AuthRepository;

@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;

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
}