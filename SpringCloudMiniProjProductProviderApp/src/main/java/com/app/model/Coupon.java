package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupontab")
public class Coupon {

	@Id
	private Integer cid;
	private String code;	
	private Double discAmt;
	private String expDate;
	public Coupon() {
		super();
	}
	public Coupon(Integer cid, String code, Double discAmt, String expDate) {
		super();
		this.cid = cid;
		this.code = code;
		this.discAmt = discAmt;
		this.expDate = expDate;
	}
	
	
	public Coupon(String code) {
		super();
		this.code = code;
	}
	@Override
	public String toString() {
		return "Coupon [cid=" + cid + ", code=" + code + ", discAmt=" + discAmt + ", expDate=" + expDate + "]";
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getDiscAmt() {
		return discAmt;
	}
	public void setDiscAmt(Double discAmt) {
		this.discAmt = discAmt;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
