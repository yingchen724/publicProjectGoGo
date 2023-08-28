package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository repository;
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	//public List<Product> getAllProducts(){
	
	public ModelAndView getAllProducts(){
		List<Product> data=null;
		data=repository.findAll();
		
		//過濾器
		//List<Product> data2= data.stream().filter(p->p.getRemark()!=null).collect(Collectors.toList());
		List<Product> data2= data.stream().filter(p->p.getPath().contains("jpg")).collect(Collectors.toList());
		
		ModelAndView viewModel=new ModelAndView("productView");
		viewModel.addObject("products",data2);		
		System.out.println(data2);
		
		//return data2;
		return viewModel;
	}

}
