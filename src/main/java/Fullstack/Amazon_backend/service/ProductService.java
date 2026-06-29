package Fullstack.Amazon_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Fullstack.Amazon_backend.entity.ProductTableEntity;
import Fullstack.Amazon_backend.repository.ProductRepository;

@Service 
//this annotation is used to indicate that this class is a service component in the spring framewor, it is a
//specialized form of the @Component annotation, which is used to mark a class as a bean and allow spring to manage its lifecycle. By using @Service, we can indicate that this class contains business logic and should be treated as a service layer in the application architecture.
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	public List<ProductTableEntity> getProductsService() {
		
		return productRepository.findAll();
		
	}
	
	public List<ProductTableEntity> getProductsByCategoryId(int categoryId) {
		
		return productRepository.findByCategoryId(categoryId);
		
	}

}
