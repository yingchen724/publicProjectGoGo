package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@Column(name="productid")
	String productid;
	String category;
	String productname;
	String description;
	Integer sales;
	Integer price;
	String color;
	Integer stock;
	String remark;
	String path;


	
}