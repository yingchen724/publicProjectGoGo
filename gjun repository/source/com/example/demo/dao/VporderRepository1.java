package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Vporder1;

public interface VporderRepository1
		extends JpaRepository<Vporder1,Integer> {
	
	List<Vporder1> findByMemberid(int memberid);
	
	List<Vporder1> findByEmployeeid(int employeeid);
}
