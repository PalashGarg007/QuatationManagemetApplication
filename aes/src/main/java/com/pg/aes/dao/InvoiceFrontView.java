package com.pg.aes.dao;

import java.time.LocalDate;

public class InvoiceFrontView {
	Integer billNo;
	Integer orderNo;
	LocalDate billDate;
	LocalDate orderDate;
	String location;
	Boolean emb;
	Boolean accepted;
	
	public InvoiceFrontView() {
		
	}
	
	public InvoiceFrontView(Integer billNo, Integer orderNo, LocalDate billDate, LocalDate orderDate, String location,
			Boolean emb, Boolean accepted) {
		this.billNo = billNo;
		this.orderNo = orderNo;
		this.billDate = billDate;
		this.orderDate = orderDate;
		this.location = location;
		this.emb = emb;
		this.accepted = accepted;
	}
	public Integer getBillNo() {
		return billNo;
	}
	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public LocalDate getBillDate() {
		return billDate;
	}
	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean getEmb() {
		return emb;
	}
	public void setEmb(Boolean emb) {
		this.emb = emb;
	}
	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "InvoiceFrontView [billNo=" + billNo + ", orderNo=" + orderNo + ", billDate=" + billDate + ", orderDate="
				+ orderDate + ", location=" + location + ", emb=" + emb + "]";
	}
	
	
}
