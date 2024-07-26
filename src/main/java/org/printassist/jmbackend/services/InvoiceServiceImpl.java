package org.printassist.jmbackend.services;

import lombok.AllArgsConstructor;
import org.printassist.jmbackend.repositories.InvoiceRepository;
import org.printassist.jmbackend.repositories.entities.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
    InvoiceRepository invoiceRepository;

    @Override
    public void createInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(long id) {
        return null;
    }

    @Override
    public Invoice updateInvoice(long id, Invoice invoice) {
        return null;
    }

    @Override
    public void deleteInvoice(long id) {

    }

    @Override
    public List<Invoice> getAllInvoices() {
        return List.of();
    }

    @Override
    public Invoice findInvoiceByEmailAddress(String emailAddress) {
        return null;
    }

    @Override
    public void deleteInvoiceByEmailAddress(String emailAddress) {

    }
}
