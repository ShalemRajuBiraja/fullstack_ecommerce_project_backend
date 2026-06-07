package Fullstack.Amazon_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Fullstack.Amazon_backend.entity.UserTableEntity;


public interface AuthRepository extends JpaRepository<UserTableEntity, Integer> {

	Optional<UserTableEntity>  findByEmail(String email);
	Optional<UserTableEntity>  findByMobileNumber(String mobileNumber);
}
