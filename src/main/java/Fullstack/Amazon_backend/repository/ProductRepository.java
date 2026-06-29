package Fullstack.Amazon_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Fullstack.Amazon_backend.entity.ProductTableEntity;


public interface ProductRepository extends JpaRepository<ProductTableEntity, Integer> {
	
	List<ProductTableEntity> findByCategoryId(int categoryId);

}
