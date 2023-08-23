package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Porder;

public interface PorderRepository
		 extends JpaRepository<Porder,Integer>{
	
	Porder findByPorderid(String porderid);
}
