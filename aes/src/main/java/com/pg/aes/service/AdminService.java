package com.pg.aes.service;

import org.springframework.data.domain.Page;
import com.pg.aes.dao.InvoiceFrontView;
import com.pg.aes.entity.Invoice;

public interface AdminService{
	/*View all invoice*/
	Page<InvoiceFrontView> allInvoice(Integer pageNo);
	
	/*View all invoices from a location*/
	Page<InvoiceFrontView> allInvoiceFromLocation(Integer pageNo, String location);
	
	/*View the invoice with the given billNo*/
	InvoiceFrontView getInvoiceByBillNo(Integer billNo);
	
	/*View the invoice with the given orderNo*/
	InvoiceFrontView getInvoiceByOrderNo(Integer orderNo);
	
	/*Add new invoice into the DB*/
	InvoiceFrontView addInvoice(Invoice invoice);

	/*Update the invoice*/
	InvoiceFrontView updateInvoice(Integer billNo, Invoice invoice);
	
	/*Delete the given invoice*/
	void deleteInvoice(Integer billNo);
	
	/*Set Emb for the given OrderNo*/
	void setEmb(Boolean emb, Integer billNo);

	/*Set Accept for the given OrderNo*/
	void setAccept(Boolean accept, Integer billNo);
	
}
