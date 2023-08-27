package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	//查詢所有
	@GetMapping("getAll")
	public List<Item> getAllItem(HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		return cart;
	}

	//確認購物時，回傳確認購買的商品
	@PostMapping(value = "confirmorder")
	public List<Item> addOorder(HttpSession session,@RequestBody List<Item> cartItems) throws IOException {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		System.out.println(cart); // 輸出 cart 內容
//		response.sendRedirect("../../confirm.html");
	    for (Item item : cart) {
	        String description = item.getProduct().getDescription();
	        int quantity = item.getQuantity();
	        double price = item.getProduct().getPrice();
	        double itemTotal = quantity * price;
	        
	        System.out.println("Description: " + description);
	        System.out.println("Quantity: " + quantity);
	        System.out.println("Price: " + price);
	        System.out.println("Item Total: " + itemTotal);	        
	    }
	    //
	    
	    List<Item> carttemp = cart.stream().filter(
	    		item1->cartItems.stream().anyMatch(
	    				item2->item2.getProduct().getDescription().equals(item1.getProduct().getDescription())
	    		)
	    ).collect(Collectors.toList());
//	    System.out.println(cart);
//	    System.out.println(cartItems);
//	    System.out.println(carttemp);
	    session.setAttribute("cartconfirm", carttemp);
		return carttemp;
	}
	
}
