package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "product")
public class Product2 {
	
	@Id
	private String productid;
	private String category;
	private String productname;
	private String description;
	private Integer sales;
	private Integer price;
	private String color;
	private Integer stock;
	private String remark;
	private String path;
	
	public Product2() {
		
	}
	
	public Product2(String productid, String category, String productname, String description, Integer sales,
			Integer price, String color, Integer stock, String remark, String path) {
		
		this.productid = productid;
		this.category = category;
		this.productname = productname;
		this.description = description;
		this.sales = sales;
		this.price = price;
		this.color = color;
		this.stock = stock;
		this.remark = remark;
		this.path = path;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
