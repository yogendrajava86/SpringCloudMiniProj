package com.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="prodtab")
public class Product {

	@Id
	private Integer prodId;
	private String prodCode;
	private Double prodCost;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cid")
	private Coupon cop;

	public Product() {
		super();
	}

	public Product(Integer prodId, String prodCode, Double prodCost, Coupon cop) {
		super();
		this.prodId = prodId;
		this.prodCode = prodCode;
		this.prodCost = prodCost;
		this.cop = cop;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public Double getProdCost() {
		return prodCost;
	}

	public void setProdCost(Double prodCost) {
		this.prodCost = prodCost;
	}

	public Coupon getCop() {
		return cop;
	}

	public void setCop(Coupon cop) {
		this.cop = cop;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodCode=" + prodCode + ", prodCost=" + prodCost + ", cop=" + cop + "]";
	}
	
	 
}
