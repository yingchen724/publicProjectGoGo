package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.VporderRepository1;
import com.example.demo.model.Vporder1;

@CrossOrigin(origins="http://127.0.0.1:5500/")
@RestController
@RequestMapping("/vporder")
public class VporderController1 {
	@Autowired
	private VporderRepository1 repository;
	
	//依memberid查詢vporder: 會員顯示訂單資訊
	@GetMapping("/mem/{memberid}")
	public List<Vporder1> getMemberVporder(@PathVariable int memberid) {
		return repository.findByMemberid(memberid);
	}
	
//	//依employeeid查詢vporder: 員工顯示負責的訂單資訊
//	@GetMapping("emp/{employeeidid}")
//	public List<Vporder1> getEmployeeVporder(@PathVariable int employeeidid) {
//		return repository.findByEmployeeid(employeeidid);
//	}
	
	//查詢所有vporder: 會員顯示訂單資訊、後台顯示訂單管理
	@GetMapping("/emp")
	public List<Vporder1> getAll() {
		return repository.findAll();
	}
}
