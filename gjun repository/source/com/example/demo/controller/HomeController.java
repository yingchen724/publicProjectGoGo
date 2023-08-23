package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	MemberRepository repo;
	
	@GetMapping
	public String home() {
		return "memberAllPorderDetail.html";
	}
	
//	@GetMapping("/comm/{orderdetailid}")
//	public String home2() {
//		return "comment.html";
//		//session.setAttribute("id", orderdetailid);
//		
//	}
	
	@PostMapping("/setSessionDid/{id}")
	public void home2(HttpSession session,@PathVariable("id") int id) {
		System.out.println(id);
		session.setAttribute("id", id);
		System.out.println(session.getAttribute("id"));
	}
	
	@PostMapping("/sessionOrderdetailid")
	@ResponseBody
	public int getOrderdetailid(HttpSession session) {
		return (Integer)session.getAttribute("id");
	}
	
	//抓Session中的Member物件回傳
	@PostMapping("/sessionMember")
	@ResponseBody
	public Member getMember(HttpSession session) {
		String email = (String)session.getAttribute("email");
		return repo.findByEmail(email);
	}
	
//	@GetMapping("/setSession/{email}/{password}")
//	public String setSession(@PathVariable("email") String email, 
//			@PathVariable("password") String password) {
//		Member m = new Member();
//		m.setEmail(email);
//		m.setPassword(password);
//		return "redirect:/";
//	}
}
