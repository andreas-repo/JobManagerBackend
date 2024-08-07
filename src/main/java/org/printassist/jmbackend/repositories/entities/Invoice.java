package org.printassist.jmbackend.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoices")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "first_name")
    private String firstNameCustomer;
    @Column(name = "last_name")
    private String lastNameCustomer;
    @Column(name = "email_address")
    private String emailAddressCustomer;
    @Column(name = "phone_number")
    private String phoneNumberCustomer;
    @Column(name = "printer_type")
    private String printerTypeCustomer;
    @Column(name = "date_of_service_renderet")
    private String dateOfServiceRenderet;
    @Column(name = "start_time")
    private String startTimeOfService;
    @Column(name = "end_time")
    private String endTimeOfService;
    @Column(name = "notes")
    private String notes;
    @Column(name = "total_cost")
    private String totalCost;
    @Column(name = "payment_method")
    private String paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_number", referencedColumnName = "id")
    private InvoiceNumber invoiceNumber;
}
