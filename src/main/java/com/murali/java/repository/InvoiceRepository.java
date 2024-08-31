package com.murali.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.murali.java.entity.Invoice;

/**
 * @author: Sanapala Muralidharan
 * @date: 31 Aug 2024 12:21:57 pm
 * @version:3.x
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // Update is Non-Select Operation, so @Modifying is used
    @Modifying
    @Query("UPDATE Invoice SET number=:number WHERE id=:id")
    Integer updateInvoiceNumberById(String number, Long id);
}
