package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;

@RestController
public class ProductController2 {
	
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

			
	//單項商品頁
	@RequestMapping(value="/productRetrieveAll/{productid}",method=RequestMethod.GET)
	//public Product findbyemail(HttpSession session,@PathVariable("productid")String productid){
	
	//public ModelAndView  findbyemail(@ModelAttribute Product product){
	public ModelAndView findbyemail(@PathVariable("productid")String productid){
	
	
		//抓session
		//String attrEmail=(String) session.getAttribute("attrProduct");
		//List<Product> productdetail = productRepo.findAll();			
		//Product m=productdetail.stream().filter(p->p.getProductid().equals(productid)).findFirst().get();
		
		//法1
		//Product P=productRepo.getById(productid);
		//return (Product)productRepo.findById(productid);
		//Product P=productRepo.findById(productid).get();
		
		//法2
		//ModelAndView m=new ModelAndView("redirect:/productdetail.html");
		ModelAndView m=new ModelAndView("productdetail");
		Product P=productRepo.findById(productid).get();
		m.addObject("product",P);
		System.out.println(P);
		//return P;
		return m;
		
		
	}
	
	//public ModelAndView findproductbyid()
	//單項商品頁
	
	
	//static test.html測試
	@RequestMapping(value = {"/productmix"}, method = RequestMethod.GET)
	  public ModelAndView test() throws SQLException{
		 ModelAndView M=new ModelAndView("test.html");
		 
		return M;
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
		  
		  //@RequestMapping(value = "/productUpdate/{productid}", method = RequestMethod.POST)
	  //public ModelAndView processFormUpdate(@PathVariable(value = "id") String id, @ModelAttribute Product p) throws SQLException {
			  
		  ModelAndView model = new ModelAndView("redirect:/productRetrieveAll");
		      
		      
			  //Optional<Product> x=productRepo.findById(id);
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
