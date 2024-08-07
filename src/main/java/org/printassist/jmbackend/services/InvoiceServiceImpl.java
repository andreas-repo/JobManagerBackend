package org.printassist.jmbackend.services;

import lombok.AllArgsConstructor;
import org.printassist.jmbackend.repositories.InvoiceRepository;
import org.printassist.jmbackend.repositories.entities.Invoice;
import org.printassist.jmbackend.repositories.entities.InvoiceNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService{

    private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
    InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        invoice.setInvoiceNumber(new InvoiceNumber(UUID.randomUUID()));
        return invoiceRepository.save(invoice);
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
