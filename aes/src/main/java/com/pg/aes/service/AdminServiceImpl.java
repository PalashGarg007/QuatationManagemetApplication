package com.pg.aes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pg.aes.dao.InvoiceFrontView;
import com.pg.aes.entity.Invoice;
import com.pg.aes.exceptions.InvalidParameterException;
import com.pg.aes.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	final Integer PAGE_SIZE = 10;

	@Override
	public Page<InvoiceFrontView> allInvoice(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
		return adminRepository.findAllInvoice(pageable)
				.map(invoice -> new InvoiceFrontView(invoice.getBillNo(),
						invoice.getOrderNo(), invoice.getBillDate(),
						invoice.getOrderDate(), invoice.getLocation(),
						invoice.getEmb(), invoice.getAccepted()));
	}

	@Override
	@Transactional
	public InvoiceFrontView addInvoice(Invoice invoice) {
		Invoice oldInvoice = adminRepository.findById(invoice.getBillNo()).orElse(null);
		
		if(oldInvoice!=null) throw new InvalidParameterException("billNo already exists");
		
		invoice.setEmb(true);
		adminRepository.save(invoice);
		
		return new InvoiceFrontView(invoice.getBillNo(),
				invoice.getOrderNo(), invoice.getBillDate(), invoice.getOrderDate(),
				invoice.getLocation(), invoice.getEmb(), invoice.getAccepted());
	}

	@Override
	@Transactional
	public InvoiceFrontView updateInvoice(Integer billNo, Invoice newInvoice) {
		Invoice oldInvoice = adminRepository.findById(billNo).orElse(null);
		if(oldInvoice==null) throw new InvalidParameterException("billNo does not exists");
		
		if(newInvoice.getEmb() != null) oldInvoice.setEmb(newInvoice.getEmb());
		if(newInvoice.getAmount() != null) oldInvoice.setAmount(newInvoice.getAmount());
		if(newInvoice.getBillDate() != null) oldInvoice.setBillDate(newInvoice.getBillDate());
		if(newInvoice.getHsnCode() != null) oldInvoice.setHsnCode(newInvoice.getHsnCode());
		if(newInvoice.getLocation() != null) oldInvoice.setLocation(newInvoice.getLocation());
		if(newInvoice.getOrderDate() != null) oldInvoice.setOrderDate(newInvoice.getOrderDate());
		if(newInvoice.getParticulars() != null) oldInvoice.setParticulars(newInvoice.getParticulars());
		if(newInvoice.getQuantity() != null) oldInvoice.setQuantity(newInvoice.getQuantity());
		if(newInvoice.getRate() != null) oldInvoice.setRate(newInvoice.getRate());
		
		newInvoice = adminRepository.save(oldInvoice);
		
		return new InvoiceFrontView(newInvoice.getBillNo(),
				newInvoice.getOrderNo(), newInvoice.getBillDate(), newInvoice.getOrderDate(),
				newInvoice.getLocation(), newInvoice.getEmb(), newInvoice.getAccepted());
	}

	@Override
	@Transactional
	public void deleteInvoice(Integer billNo) {
		adminRepository.deleteById(billNo);	
	}

	
	@Override
	public Page<InvoiceFrontView> allInvoiceFromLocation(Integer pageNo, String location) {
		Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
		return adminRepository.findAllInvoiceFromLocation(pageable, location)
				.map(invoice -> new InvoiceFrontView(invoice.getBillNo(),
						invoice.getOrderNo(), invoice.getBillDate(),
						invoice.getOrderDate(), invoice.getLocation(),
						invoice.getEmb(), invoice.getAccepted()));
	}

	@Override
	public InvoiceFrontView getInvoiceByBillNo(Integer billNo) {
		return adminRepository.findById(billNo)
				.map(invoice -> new InvoiceFrontView(invoice.getBillNo(),
						invoice.getOrderNo(), invoice.getBillDate(),
						invoice.getOrderDate(), invoice.getLocation(),
						invoice.getEmb(), invoice.getAccepted()))
				.orElse(null);
	}

	@Override
	public InvoiceFrontView getInvoiceByOrderNo(Integer orderNo) {
		List<InvoiceFrontView> invoices = adminRepository.findByOrderNo(orderNo)
				.stream()
				.map(invoice -> new InvoiceFrontView(invoice.getBillNo(),
						invoice.getOrderNo(), invoice.getBillDate(),
						invoice.getOrderDate(), invoice.getLocation(),
						invoice.getEmb(), invoice.getAccepted()))
				.toList();
		
		return (invoices.isEmpty())? null : invoices.get(0);
				
	}

	@Override
	@Transactional
	public void setEmb(Boolean emb, Integer billNo) {
		Invoice oldInvoice = adminRepository.findById(billNo).orElse(null);
		if(oldInvoice==null) throw new InvalidParameterException("billNo does not exists");
		
		oldInvoice.setEmb(emb);
		adminRepository.save(oldInvoice);
		
	}

	@Override
	@Transactional
	public void setAccept(Boolean accept, Integer billNo) {
		Invoice oldInvoice = adminRepository.findById(billNo).orElse(null);
		if(oldInvoice==null) throw new InvalidParameterException("billNo does not exists");
		
		oldInvoice.setAccepted(accept);
		adminRepository.save(oldInvoice);
	}
	
}
