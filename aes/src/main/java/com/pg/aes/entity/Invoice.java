package com.pg.aes.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Invoice")
public class Invoice {
	
	@Id
	private Integer billNo;
	private Integer orderNo;
	private LocalDate billDate;
	private LocalDate orderDate;
	private String location;
	private String particulars;
	private String hsnCode;
	private Integer quantity;
	private Double rate;
	private Double amount;
	private Boolean emb;
	private Boolean accepted;
	
	public Invoice() {
	}
	
	public Invoice(Integer billNo, Integer orderNo, LocalDate billDate, LocalDate orderDate, String location,
			String particulars, String hsnCode, Integer quantity, Double rate, Double amount, Boolean emb,
			Boolean accepted) {
		this.billNo = billNo;
		this.orderNo = orderNo;
		this.billDate = billDate;
		this.orderDate = orderDate;
		this.location = location;
		this.particulars = particulars;
		this.hsnCode = hsnCode;
		this.quantity = quantity;
		this.rate = rate;
		this.amount = amount;
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

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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
		return "Invoice [billNo=" + billNo + ", orderNo=" + orderNo + ", billDate=" + billDate + ", orderDate="
				+ orderDate + ", location=" + location + ", particulars=" + particulars + ", hsnCode=" + hsnCode
				+ ", quantity=" + quantity + ", rate=" + rate + ", amount=" + amount + ", emb=" + emb + "]";
	}
	
}
