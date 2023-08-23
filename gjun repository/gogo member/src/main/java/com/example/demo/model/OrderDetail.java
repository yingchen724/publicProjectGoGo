package com.example.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="orderdetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="porderid", referencedColumnName="porderid")
	private Porder porder;
	//private Integer porderid;
	
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JsonIgnore
//	@JoinColumn(name="productid", referencedColumnName="productid")
//	private Product product;
	private Integer productid;
	
	private Integer quantity;
	
	private Integer score;
	
	private String message;
	
	private Date sdate;

	public OrderDetail() {
	}
	
	//會員訂購時
	public OrderDetail(Porder porder, Integer productid, Integer quantity) {
		this.porder = porder;
		this.productid = productid;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Porder getPorder() {
		return porder;
	}

	public void setPorder(Porder porder) {
		this.porder = porder;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", productid=" + productid + ", quantity=" + quantity
				+ ", score=" + score + ", message=" + message + ", sdate=" + sdate + "]";
	}
	
//	//會員評價時--評分+輸入內容
//	public void setComment(Integer score, String message) {
//		this.score = score;
//		this.message = message;
//		sdate = new Date();
//	}
//	
//	//會員評價時--評分
//	public void setComment(Integer score) {
//		this.score = score;
//		sdate = new Date();
//	}
	
	
}
