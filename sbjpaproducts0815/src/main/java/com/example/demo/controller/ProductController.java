package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository repository;
	
	//查詢所有商品
	@GetMapping("/products")
	public ModelAndView getAllProducts() {
		List<Product> products = repository.findAll();
		ModelAndView viewModel = new ModelAndView("productView");
		viewModel.addObject("products", products);
		return viewModel;
	}
}
