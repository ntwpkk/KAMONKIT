package com.kamonkit.main.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "CUSTOMER_SEQ")
@Entity(name = "CUSTOMER")
@Table(indexes = {@Index(name = "CUSTOMER_idx_customerId_unique", columnList = "customerId", unique = true)})
public class Customer extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362722861337980698L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
	private long customerId;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	private Address workplace;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contract = new HashSet<Contract>();
	@OneToMany(mappedBy = "guarantor",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contractG = new HashSet<Contract>();
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "name_title_id")
	private NameTitle nameTitle;
	
	
	public Customer() {}
	
	public Customer(String citizenId, String fName, String lName, String telephone, Date birthday) {
		super();
		this.telephone = telephone;
		this.birthDay = birthday;
		this.fName = fName;
		this.lName = lName;
		this.citizenId = citizenId;
	}
	

}
