package com.example.demo.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.Method;
import com.example.demo.dao.PorderRepository;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Porder;

@CrossOrigin
@RestController
@RequestMapping("/porder")
public class PorderController {
	@Autowired
	private PorderRepository repository;

	//查詢所有訂購單
	@GetMapping
	public List<Porder> getAll() {
		return repository.findAll();
	}
	
	
	
	//新增訂購單: 會員訂購商品
	//修改訂購單: 後台員工修改訂單內容
	@PostMapping
	public ResponseEntity<Porder> savePorder(@RequestBody Porder porder) {
		//新增
		if(porder.getPorderid()==null) {
			Date orderdate = new Date();
			porder.setOrderdate(orderdate);
			String temp = "GG"+Method.formatDate(orderdate.getYear()%100)
					+Method.formatDate(orderdate.getMonth()+1)
					+Method.formatDate(orderdate.getDate());
			//找下一個porder
			for(int i=1; i<=999; i++) {
				if(repository.findByPorderid(temp+Method.formatThree(i))==null) {
					porder.setPorderid(temp+Method.formatThree(i));
					break;
				}
			}
		}
		repository.save(porder);
		return new ResponseEntity<Porder>(porder,HttpStatus.OK);
	}

}
