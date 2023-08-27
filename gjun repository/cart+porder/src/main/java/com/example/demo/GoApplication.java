package com.example.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.PorderRepository;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Porder;

@SpringBootApplication
public class GoApplication implements CommandLineRunner {
	@Autowired
	private PorderRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(GoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Porder p = new Porder(2, 1, "Taipei");
//		p.setPorderid("GG230820001");
//		p.setOrderdate(new Date());
//		OrderDetail od1 = new OrderDetail(p,1001, 3);
//		OrderDetail od2 = new OrderDetail(p,1002, 2);
//		Set<OrderDetail> orderDetails = new HashSet<>();
//		orderDetails.add(od1);
//		orderDetails.add(od2);
//		p.setOrderDetails(orderDetails);
//		repository.save(p);
	}

}
