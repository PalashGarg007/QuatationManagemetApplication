package com.pg.aes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pg.aes.dao.InvoiceFrontView;
import com.pg.aes.entity.Invoice;
import com.pg.aes.service.AdminService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/")
	public String admin() {
		return "admin";
		
	}
	
//	@GetMapping(value = "/allInvoice/page/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<InvoiceFrontView>> allInvoice(@PathVariable Integer pageNo) {
//		List<InvoiceFrontView> invoices = adminService.allInvoice(pageNo - 1).getContent();
//		
//		logger.info("List of all the invoice on "+ pageNo +" :\n"+ invoices);
//		
//		return (invoices.isEmpty()) ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null):
//			ResponseEntity.ok(invoices);
//	}
	
	@GetMapping("/allInvoice/page/{pageNo}")
	public ModelAndView allInvoice(@PathVariable Integer pageNo) {
		List<InvoiceFrontView> invoices = adminService.allInvoice(pageNo - 1).getContent();
		
		ModelAndView modelAndView = new ModelAndView("invoiceList");
		modelAndView.addObject("invoices", invoices);
	    modelAndView.addObject("pageNo", pageNo);
		
		logger.info("List of all the invoice on "+ pageNo +" :\n"+ invoices);
		
		return modelAndView;
	}
	
	@GetMapping(value = "/allInvoiceFromLocation/location/{location}/page/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceFrontView>> allInvoiceFromLocation(@PathVariable Integer pageNo, @PathVariable String location) {
		List<InvoiceFrontView> invoices = adminService.allInvoiceFromLocation(pageNo-1, location).getContent();
		
		logger.info("List of all the invoice on "+ pageNo +"from "+ location +":\n"+ invoices);
		
		return (invoices.isEmpty())? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null):
			ResponseEntity.ok(invoices);
	}
	
	@GetMapping(value = "/getInvoiceByBillNo/id/{billNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceFrontView> getInvoiceByBillNo(@PathVariable Integer billNo) {
		InvoiceFrontView invoice = adminService.getInvoiceByBillNo(billNo);
		
		logger.info("Invoice for the billNo "+ billNo +":\n"+ invoice);
		
		return (invoice==null)? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):
			ResponseEntity.ok(invoice);
	}
	
	@GetMapping(value = "/getInvoiceByOrderNo/id/{orderNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceFrontView> getInvoiceByOrderNo(@PathVariable Integer orderNo) {
		InvoiceFrontView invoice = adminService.getInvoiceByOrderNo(orderNo);
		
		logger.info("Invoice for the billNo "+ orderNo +":\n"+ invoice);
		
		return (invoice==null)? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):
			ResponseEntity.ok(invoice);
	}
	
	@GetMapping(value = "/getInvoice/search/{parameter}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InvoiceFrontView>> getInvoiceBySearch(@PathVariable String parametere){
		//dynamic str search in int
		//dynamic search in text, ignore if int
		//concate the list
		return null;
	}
	
	@PostMapping(value = "/addInvoice", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceFrontView> addInvoice(@RequestBody Invoice invoice) {
		
		InvoiceFrontView newInvoice = adminService.addInvoice(invoice);
		
		logger.info("Created invoice for BillNo: "+ invoice.getBillNo() +".\n");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newInvoice);
	}
	
	@PutMapping(value = "/updateInvoice/id/{billNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceFrontView> updateInvoice(@PathVariable Integer billNo, @RequestBody Invoice invoice){
		InvoiceFrontView updatedInvoice = adminService.updateInvoice(billNo, invoice);
		
		logger.info("Updated invoice for BillNo: "+ billNo +".\n");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedInvoice);
	}
	
	@PostMapping(value = "/setEmb/id/{billNo}/value/{emb}")
	@ResponseStatus(code = HttpStatus.OK)
	public void setEmb(@PathVariable Boolean emb, @PathVariable Integer billNo) {
		adminService.setEmb(emb, billNo);
		
		logger.info("Set Emb="+ emb +" for orderNo="+billNo);
	}
	
	@PostMapping(value = "/setAccept/id/{billNo}/value/{accept}")
	@ResponseStatus(code = HttpStatus.OK)
	public void setAccept(@PathVariable Boolean accept, @PathVariable Integer billNo) {
		adminService.setAccept(accept, billNo);
		
		logger.info("Set Accept="+ accept +" for orderNo="+billNo);
	}
	
	@DeleteMapping(value = "/deleteInvoice/id/{billNo}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteInvoice(@PathVariable Integer billNo) {
		adminService.deleteInvoice(billNo);
		
		logger.info("Deleted invoice for BillNo: "+ billNo +".\n");
	}
	
	//	/getInvoiceFromSearch/{str} 
	
}
