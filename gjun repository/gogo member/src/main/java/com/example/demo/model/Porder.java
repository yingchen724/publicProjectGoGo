package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="porder")
@Data
public class Porder {
	@Id
	private String porderid;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnoreProperties
	@JoinColumn(name="memberid", referencedColumnName="id")
	private Member member;
	//private Integer memberid;

	private Date orderdate;
	
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy="porder", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Porder() {}
		
	public Porder(Member member, String address) {
		this.member = member;
		this.address = address;
	}

	
}
