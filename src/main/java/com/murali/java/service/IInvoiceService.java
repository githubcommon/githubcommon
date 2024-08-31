package com.murali.java.service;

import java.util.List;

import com.murali.java.entity.Invoice;

/**
 * @author: Sanapala Muralidharan
 * @date: 31 Aug 2024 12:22:57 pm
 * @version:3.x
 */
public interface IInvoiceService {

    /**
     * Takes Invoice Object as input and returns PK generated
     */
    Long saveInvoice(Invoice inv);

    /**
     * Takes existing Invoice data as input and updates values
     */
    void updateInvoice(Invoice e);

    /**
     * Takes PK(ID) as input and deletes Invoice Object data
     */
    void deleteInvoice(Long id);

    /**
     * Takes id as input and returns one row as one object
     */
    Invoice getOneInvoice(Long id); // used in RestController

    /**
     * select all rows and provides result as a List<Invoice>
     */
    List<Invoice> getAllInvoices();

    /**
     * Takes Id as input,checks if record exists returns true, else false
     * 
     */
    boolean isInvoiceExist(Long id);

    /**
     * Takes 2 fields as input, updates Invoice data as provided where clause like
     * 'UPDATE Invoice SET number=:number WHERE id=:id'
     */
    Integer updateInvoiceNumberById(String number, Long id);
}
