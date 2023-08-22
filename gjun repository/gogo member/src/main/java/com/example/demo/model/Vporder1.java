package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

//會員顯示訂單用
@Entity
@Immutable
@Subselect("SELECT det.id, por.porderid, por.memberid, det.productid, det.quantity, por.employeeid, por.orderdate, por.address, det.sdate FROM gogo.porder AS por JOIN gogo.orderdetail AS det ON por.porderid = det.porderid")
public class Vporder1 {
	@Id
	private Integer id;

	private String porderid;

	private Integer memberid;

	private Integer productid;

	private Integer quantity;

	private Integer employeeid;

	private Date orderdate;
	
	private String address;
	
	private Date sdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPorderid() {
		return porderid;
	}

	public void setPorderid(String porderid) {
		this.porderid = porderid;
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
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

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "Vporder1 [id=" + id + ", porderid=" + porderid + ", memberid=" + memberid + ", productid=" + productid
				+ ", quantity=" + quantity + ", employeeid=" + employeeid + ", orderdate=" + orderdate + ", address="
				+ address + ", sdate=" + sdate + "]";
	}

	
}