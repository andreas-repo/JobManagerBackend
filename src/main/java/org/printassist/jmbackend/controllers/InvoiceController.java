package org.printassist.jmbackend.controllers;

import lombok.AllArgsConstructor;
import org.printassist.jmbackend.repositories.entities.Invoice;
import org.printassist.jmbackend.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
    private InvoiceService invoiceService;

    @PostMapping("/createInvoice")
    public void createInvoice(@RequestBody Invoice invoice) {
        if (invoice == null) {
            LOGGER.error("Invoice is null");
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        invoiceService.createInvoice(invoice);
        //return new ResponseEntity<>(HttpStatus.OK);
    }
}
