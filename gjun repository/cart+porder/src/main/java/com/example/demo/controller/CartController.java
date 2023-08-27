package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	ProductRepository repository;

	@Autowired
	HttpServletResponse response;

	@RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
	public List<Item> buy(@PathVariable("id") String id, HttpSession session) throws IOException {
		List<Item> cart = null;

		// 判斷session是否有值
		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<Item>();
			System.out.println(id);
			// 抓取prouct id
			Product p = repository.findById(id).get();
			System.out.println(p);
			// 把prouct跟數量1存取到list<Item>裡的cart
			cart.add(new Item(p, 1));
			session.setAttribute("cart", cart);

		} else {
			// session的cart存取到List<Item> cart
			cart = (List<Item>) session.getAttribute("cart");
			int index = this.exists(id, cart);
			if (index == -1) {
				Product p = repository.findById(id).get();
				cart.add(new Item(p, 1));
			} else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);

		}
		response.sendRedirect("../../cart.html");
		return cart;
	}

	private int exists(String id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getProductid().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public List<Item> remove(@PathVariable("id") String id, HttpSession session) throws IOException {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(id, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		response.sendRedirect("../../cart.html");
		return cart;
	}

	@RequestMapping(value = "update/{id}/{qty}", method = RequestMethod.GET)
	public List<Item> update(@PathVariable("id") String id, @PathVariable("qty") Integer qty, HttpSession session)
			throws IOException {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = this.exists(id, cart);
		cart.get(index).setQuantity(qty);
		session.setAttribute("cart", cart);
		response.sendRedirect("../../../cart.html");
		return cart;
	}

	@GetMapping("getAll")
	public List<Item> getAllItem(HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		return cart;
	}



}
