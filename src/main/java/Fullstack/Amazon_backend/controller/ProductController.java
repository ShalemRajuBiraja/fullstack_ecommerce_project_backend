package Fullstack.Amazon_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Fullstack.Amazon_backend.entity.ProductTableEntity;
import Fullstack.Amazon_backend.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/products")
	public List<ProductTableEntity> getProducts( @RequestParam(required = false) int categoryId) {
		
		if(categoryId != 0) {
			return productService.getProductsByCategoryId(categoryId);
		}
		
		
		List<ProductTableEntity> productServiceResponse = productService.getProductsService();
		
		return productServiceResponse;
		
	}

}
