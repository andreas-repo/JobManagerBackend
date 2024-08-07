package org.printassist.jmbackend.services;


import org.printassist.jmbackend.repositories.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoice(long id);
    Invoice updateInvoice(long id, Invoice invoice);
    void deleteInvoice(long id);
    List<Invoice> getAllInvoices();
    Invoice findInvoiceByEmailAddress(String emailAddress);
    void deleteInvoiceByEmailAddress(String emailAddress);
}
