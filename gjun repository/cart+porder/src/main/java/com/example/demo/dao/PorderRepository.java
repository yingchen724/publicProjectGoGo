package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Member;
import com.example.demo.model.MemberRepository;
import com.example.demo.model.Porder;

public interface PorderRepository
		 extends JpaRepository<Porder,Integer>{
	
	
	Porder findByPorderid(String porderid);
	
	List<Porder> findByMember(Member member);
}
