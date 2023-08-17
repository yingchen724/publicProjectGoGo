package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Item;
import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ProductRepository repository;
	
	//購物車內是否已經有這個商品(有:回傳id，沒有:回傳-1)
	private int exists(int id, List<Item> cart) {
		for(int i=0; i<cart.size(); i++) {
			if(cart.get(i).getProduct().getId()==id)  return i;
		}
		return -1;
	}
	
	//新增這個id的商品1個
	@GetMapping("/buy/{id}")
	public ModelAndView buy(@PathVariable("id") int id,
			HttpSession session) {
		List<Item> cart = null;
		//如果還沒有"cart"這個session attribute
		if(session.getAttribute("cart")==null) {
			cart = new ArrayList<>();
			Product p = repository.findById(id).get();
			cart.add(new Item(p,1));
			session.setAttribute("cart", cart);
		}
		//如果已經有"cart"這個session attribute
		else {
			//轉型
			cart = (List<Item>)session.getAttribute("cart");
			int index = exists(id,cart);
			//購物車內還沒有這個商品 -> 數量=1
			if(index==-1) {
				Product p = repository.findById(id).get();
				cart.add(new Item(p,1));
			}
			//購物車內已經有這個商品 -> 數量+1
			else {
				int quantity = cart.get(index).getQuantity()+1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		ModelAndView viewModel = new ModelAndView("cart");
		viewModel.addObject("cart", "cart");
		return viewModel;
	}
	
	//修改
	@GetMapping("/update/{id}/{quantiry}")
	public ModelAndView update(@PathVariable("id") int id, 
			@PathVariable("quantity") int quantity, HttpSession session) {
		List<Item> cart = (List<Item>)session.getAttribute("cart");
		int index = exists(id,cart);
		//修改數量
		cart.get(index).setQuantity(quantity);
		session.setAttribute("cart", cart);
		ModelAndView viewModel = new ModelAndView("cart");
		viewModel.addObject("cart", cart);
		return viewModel;
	}
	
	//刪除
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable int id,
			HttpSession session) {
		List<Item> cart = (List<Item>)session.getAttribute("cart");
		int index = exists(id,cart);
		//刪除購物車中的商品
		cart.remove(index);
		session.setAttribute("cart", cart);
		ModelAndView viewModel = new ModelAndView("cart");
		viewModel.addObject("cart", cart);
		return viewModel;
	}
}
