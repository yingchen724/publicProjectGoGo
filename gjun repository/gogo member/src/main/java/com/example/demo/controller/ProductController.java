package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;

@RestController
public class ProductController {
	
	@Autowired
	 ProductRepository productRepo;
	 //ProductRepository dao;
	 
//	@RequestMapping(value="/product",method=RequestMethod.GET)
//	public List<Product> getAll(){
//		return productRepo.findAll();
//	}
	

	
	//查詢SQL資料
	@RequestMapping(value = {"/productRetrieveAll","/"}, method = RequestMethod.GET)
  public ModelAndView retrieveProduct() throws SQLException{
  Iterable<Product> product = productRepo.findAll();
  
     ModelAndView model = new ModelAndView("productList");
     model.addObject("product",product);
     return model;
  }

	
	
	//新增畫面	
	@RequestMapping(value="/productCreate",method=RequestMethod.GET)
	public ModelAndView getAll(){
	//public String getAll(@ModelAttribute Product p){
		ModelAndView  mv=new ModelAndView("productCreate");
		//productRepo.save(p);
		List<Product> pro=productRepo.findAll();
		mv.addObject("product", pro);
		return mv;
		//return "redirect:/";
		

	}
	
	
	
	//新增方法
	 @RequestMapping(value = "/productCreate", method = RequestMethod.POST)
	    public ModelAndView processFormCreate(@ModelAttribute Product pro) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");
	       productRepo.save(pro);
	       model.addObject(pro);
	       return model;
	    }
	
	
	//轉到修改畫面
	 @RequestMapping(value = "/productUpdate", method = RequestMethod.GET)
		 public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") String id) {
		     ModelAndView model = new ModelAndView("productUpdate");
		     Product product = productRepo.findById(id).get();
		     model.addObject(product);
		     return model;
		}
			
	 
	 //修改方法
	  @RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
		  public ModelAndView processFormUpdate(@ModelAttribute Product p) throws SQLException {
			  ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");
			  productRepo.save(p);             
			  return model;
	    }
	 	
		 
		 //刪除
		 @RequestMapping(value = "/productDelete", method = RequestMethod.GET)
		    public ModelAndView deleteCustomer(@RequestParam(value="id", required=false, defaultValue="1") String id) {

		       ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");
		       productRepo.deleteById(id);
		       return model;
		    }

	


}
