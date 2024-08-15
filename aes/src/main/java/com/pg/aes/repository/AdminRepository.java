package com.pg.aes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pg.aes.entity.Invoice;
import java.util.List;



public interface AdminRepository extends JpaRepository<Invoice, Integer>{
	
	@Query(value = "SELECT * FROM Invoice "+
			"ORDER BY emb DESC, bill_no DESC", nativeQuery = true)
	public Page<Invoice> findAllInvoice(Pageable pageable);
	
	@Query(value = "SELECT * FROM Invoice "+
			"WHERE location Like %:location% "+
			"ORDER BY emb DESC, bill_no DESC", nativeQuery = true)
	public Page<Invoice> findAllInvoiceFromLocation(Pageable pageable, @Param("location") String location);
	
	List<Invoice> findByOrderNo(Integer orderNo);
	
}
