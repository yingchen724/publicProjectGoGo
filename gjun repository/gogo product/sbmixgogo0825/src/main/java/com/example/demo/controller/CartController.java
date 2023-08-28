package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	ProductRepository repository;
	
	@RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
	public ModelAndView buy(@PathVariable("id") String id, HttpSession session) {
		List<Item> cart=null;
		
		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<Item>();
			Product p=repository.findById(id).get();
			cart.add(new Item(p, 1));
			session.setAttribute("cart", cart);
		} else {
			cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(id, cart);
			if (index == -1) {
				Product p=repository.findById(id).get();
				cart.add(new Item(p, 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		ModelAndView mv=new ModelAndView("cart");
		mv.addObject("cart", cart);
		return mv;
	}
	 private int exists(String id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductid().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	 
	 
	 
	 
	 

	 
	 

}
