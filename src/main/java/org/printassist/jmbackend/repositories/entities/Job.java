package org.printassist.jmbackend.repositories.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "iscompleted")
	private boolean isCompleted;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "emailaddress")
	private String emailAddress;
	@Column(name = "phonenumber")
	private String phoneNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "province")
	private String province;
	@Column(name = "postalcode")
	private String postalCode;
	@Column(name = "country")
	private String country;
	@Column(name = "printertype")
	private String printerType;
	@Column(name = "date")
	private LocalDate date;
	@Column(name = "time")
	private LocalTime time;

}
