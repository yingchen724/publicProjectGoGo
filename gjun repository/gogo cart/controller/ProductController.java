package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;
//origins="http://127.0.0.1:5050/"
@CrossOrigin()
@Controller
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	
	@ResponseBody
	@GetMapping("products")
	public List<Product> getAllProducts() {
		List<Product> data = productRepository.findAll();
		return data;
	}
}