package com.kamonkit.main.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.kamonkit.main.entity.BaseDomain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "CONTRACT_SEQ")
@Entity(name = "CONTRACT")
@Table(indexes = {@Index(name = "CONTRACT_idx_contractId_unique", columnList = "contractId", unique = true)})
public class Contract extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1053657448925685697L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRACT_SEQ")
	private long contractId;
	
	private long downPayment,interest,cost,monthlyPay,numberofInstallment;
	
	private double interestRate;
	
	private String code;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "customer_id")
	private Customer guarantor;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "motorcycle_id")
	private Motorcycle motorcycle;
	
	@OneToMany(mappedBy = "contract",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installment> installment = new HashSet<Installment>();
	
//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "status_contract_id")
//	private StatusContract status;//00ขายรถสด 01ขายรถผ่อนอยู่ 02จำนำรถอยู่ 11ขายรถผ่อนปิด 12จำนำรถปิด 21ขายรถผ่อนยึด 22จำนำรถยึด
	
	@OneToMany(mappedBy = "contract",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Tracking> tracking = new HashSet<Tracking>();
	
	public Contract() {
		super();
	}

	public Contract(long downPayment, double interestRate, long interest, long cost, String code, long numberofInstallment, long pay) {
		super();
		this.downPayment = downPayment;
		this.interest = interest;
		this.cost = cost;
		this.interestRate = interestRate;
		this.code = code;
		this.numberofInstallment = numberofInstallment;
		this.monthlyPay = pay;
	}

	@Enumerated(EnumType.ORDINAL)
	ContractStatus status;

    public enum ContractStatus {
        CONTRACT,
        CLOSE,
        IMPOUND;
    }
	
	
	
	

}
