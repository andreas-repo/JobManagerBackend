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
    @Column(name = "firstName")
    private String firstNameCustomer;
    @Column(name = "lastName")
    private String lastNameCustomer;
    @Column(name = "emailAddress")
    private String emailAddressCustomer;
    @Column(name = "phoneNumber")
    private String phoneNumberCustomer;
    @Column(name = "printerType")
    private String printerTypeCustomer;
    @Column(name = "dateOfServiceRenderet")
    private String dateOfServiceRenderet;
    @Column(name = "startTime")
    private String startTimeOfService;
    @Column(name = "endTime")
    private String endTimeOfService;
    @Column(name = "notes")
    private String notes;
    @Column(name = "totalCost")
    private String totalCost;
    @Column(name = "paymentMethod")
    private String paymentMethod;
    @Column(name = "invoiceNumber")
    private String invoiceNumber;
}
