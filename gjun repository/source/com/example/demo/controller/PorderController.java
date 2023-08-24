package com.example.demo.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Method;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.PorderRepository;
import com.example.demo.model.MemberRepository;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Porder;
import com.example.demo.model.Product;
import com.example.demo.model.Member;

@CrossOrigin
@RestController
@RequestMapping("/porder")
public class PorderController {
	@Autowired
	private PorderRepository repository;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PorderRepository porderRepo;
	
	@Autowired
	private OrderDetailRepository detRepo;

	//查詢所有訂購單: 後台查詢用
	@GetMapping
	public List<Porder> getAll() {
		return repository.findAll();
	}
	
	//依memberid查詢訂單: 前台會員查詢用
	@GetMapping("/{memberid}")
	public List<Porder> getByMember(@PathVariable Integer memberid) {
		return repository.findAll().stream().filter(porder->porder.getMember().getId()==memberid)
				.collect(Collectors.toList());
	}
	
	//新增訂購單: 會員訂購商品 (要有memberid、address、orderDetail
	@PostMapping("/{memberid}")
	public ResponseEntity<Porder> savePorder(@PathVariable int memberid,
			@RequestBody Porder porder) {
		Member m = memberRepo.findById(memberid).get();
		porder.setMember(m);
		System.out.println("porder:"+porder);
		Date orderdate = new Date();
		porder.setOrderdate(orderdate);
		System.out.println("porder:"+porder);
		String temp = "GG" + Method.formatDate(orderdate.getYear() % 100) + Method.formatDate(orderdate.getMonth() + 1)
				+ Method.formatDate(orderdate.getDate());
		// 找下一個porder
		for (int i = 1; i <= 999; i++) {
			if (repository.findByPorderid(temp + Method.formatThree(i)) == null) {
				porder.setPorderid(temp + Method.formatThree(i));
				break;
			}
		}
		for(OrderDetail det:porder.getOrderDetails()) {
			det.setPorder(porder);
		}
		repository.save(porder);
		return new ResponseEntity<Porder>(porder,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
//	@GetMapping("/comm/{orderdetailid}")
//	public String home2() {
//		return "comment.html";
//		//session.setAttribute("id", orderdetailid);
//		
//	}
	
	//Session中存orderDetail的id: 轉給新增評論頁面
	@PostMapping("/setSessionDid/{id}")
	public void home2(HttpSession session,@PathVariable("id") int id) {
		System.out.println(id);
		session.setAttribute("id", id);
		System.out.println(session.getAttribute("id"));
	}
	
	
	//讀取Session中的orderDetail的id: 新增評論頁面讀取用
	@PostMapping("/sessionOrderdetailid")
	public int getOrderdetailid(HttpSession session) {
		return (Integer)session.getAttribute("id");
	}
	
	//讀取Session中的cart: 結帳頁面使用
//	@PostMapping("/sessionChart")
//	public int getChart(HttpSession session) {
//		return (List<Item>) session.getAttribute("chart");
//	}
	
	//依memberid查Member物件
//	@GetMapping("/address/{memberid}")
//	public Member findAdressByMemberid(@PathVariable int memberid) {
//		return memberRepo.findById(memberid).get();
//	}
	
	//抓Session中的Member物件回傳
	@PostMapping("/sessionMember")
	@ResponseBody
	public Member getMember(HttpSession session) {
		String email = (String)session.getAttribute("email");
		return memberRepo.findByEmail(email);
	}
	
	//回傳訂單總金額
//	@GetMapping("/allamount/{porderid}")
//	public int findAdressByMemberid(@PathVariable String porderid) {
//		return repository.findByPorderid(porderid).
//	}
	
	//修改訂單: 後台修改用
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Porder> updatePorder(HttpSession session, 
			@RequestBody Porder porder) {
		repository.save(porder);
		return new ResponseEntity<Porder>(porder,HttpStatus.OK);
	}
	
	//刪除訂單: 後台刪除用(會連帶對應的orderDetails一起刪)
	@GetMapping("/delete/{porderid}")
	public void deletePorder(@PathVariable String porderid) {
		System.out.println(porderid);
		Porder p = repository.findByPorderid(porderid);
		System.out.println(p);
		repository.delete(p);
	}
	
	//依orderDetail物件查詢Product物件
	@PostMapping("/getProduct")
	public Product getPorder(@RequestBody OrderDetail detail) {
		OrderDetail detail1 = detRepo.findById(detail.getId()).get();
		return detail1.getProduct();
	} 
	
	@GetMapping("/test")
	public int getget() {
		return 10000;
	}

}
