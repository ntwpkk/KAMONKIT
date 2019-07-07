package com.kamonkit.main.entity;



import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "ADDRESS_SEQ")
@Entity(name = "ADDRESS")
@Table(indexes = {@Index(name = "ADDRESS_idx_addressId_unique", columnList = "addressId", unique = true)})
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296354850363202783L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
	private long addressId;
	private String detail;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "subdistrict_id")
	private Subdistrict subdistrict;
//	@OneToOne
//	@PrimaryKeyJoinColumn
//	private Customer customerAddress;
//	@OneToOne
//	@PrimaryKeyJoinColumn
//	private Customer customerWorkplace;
//	@OneToOne
//	@PrimaryKeyJoinColumn
//	private Employee employeeAddress;
	
}
