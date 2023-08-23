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
public class Porder {
	@Id
	private String porderid;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="memberid", referencedColumnName="id")
	private Member member;
	//private Integer memberid;

	private Date orderdate;
	
	private String address;
	
	//應該要刪除
	//@JsonIgnore
	@OneToMany(mappedBy="porder", cascade=CascadeType.ALL/*, fetch=FetchType.EAGER*/)
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Porder() {}
		
	public Porder(Member member, String address) {
		this.member = member;
		this.address = address;
	}

	public String getPorderid() {
		return porderid;
	}

	public void setPorderid(String porderid) {
		this.porderid = porderid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	//有一對多關係時，多的那邊toString要刪除1的物件，所以不能用@Data
	@Override
	public String toString() {
		return "Porder [porderid=" + porderid + ", orderdate=" + orderdate + ", address=" + address + ", orderDetails="
				+ orderDetails + "]";
	}

	
}
