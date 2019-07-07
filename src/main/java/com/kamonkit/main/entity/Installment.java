package com.kamonkit.main.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.kamonkit.main.entity.Contract.ContractStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "INSTALLMENT_SEQ")
@Entity(name = "INSTALLMENT")
@Table(indexes = {@Index(name = "INSTALLMENT_idx_installmentId_unique", columnList = "installmentId", unique = true)})
public class Installment extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3129279522609297307L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "INSTALLMENT_SEQ")
	private long installmentId;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "contract_id")
	private Contract contract;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "service_id")
	private Service service;
	private long cost,interest,no;
	private Date date;
	
	@Enumerated(EnumType.ORDINAL)
	InstallmentStatus status;

    public enum InstallmentStatus {
        PAID,
        LATEPAID,
        UNPAID,
        LATEUNPAID;
    }
    
    
    public Installment() {}


	public Installment(long no) {
		super();
		this.no = no;
		this.status = InstallmentStatus.UNPAID;
	}
    
    
	
	
}
