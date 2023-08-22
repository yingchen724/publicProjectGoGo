package com.example.demo.controller;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.model.OrderDetail;

@CrossOrigin
@RestController
@RequestMapping("/odetail")
public class OrderDetailController {
	@Autowired
	private OrderDetailRepository repository;
	
	//新增訂購細項: 會員訂購商品
	//修改訂購細項: 後台員工修改訂單內容
	@PostMapping
	public ResponseEntity<OrderDetail> saveDetail(@RequestBody OrderDetail orderDetail) {
		repository.save(orderDetail);
		return new ResponseEntity<OrderDetail>(orderDetail,HttpStatus.OK);
	}
	
	//依id查詢訂購細項: 按"評價"按鈕後，查詢這筆資料
	@GetMapping("/{id}")
	public OrderDetail getById(@PathVariable int id) {
		return repository.findById(id).get();
	}
	
	//修改訂購細項: 會員新增評價
	//orderDetail要提供(message)、score
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<OrderDetail> setComment(@PathVariable int id, 
			@RequestBody OrderDetail orderDetail) {
		OrderDetail o = repository.findById(id).get();
		o.setMessage(orderDetail.getMessage());
		o.setScore(orderDetail.getScore());
		o.setSdate(new Date());
		repository.save(o);
		return new ResponseEntity<OrderDetail>(o, HttpStatus.OK);
	}
}
