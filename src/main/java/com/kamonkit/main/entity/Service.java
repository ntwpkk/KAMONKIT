package com.kamonkit.main.entity;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@SequenceGenerator(name = "SERVICE_SEQ")
@Entity(name = "SERVICE")
@Table(indexes = {@Index(name = "SERVICE_idx_serviceId_unique", columnList = "serviceId", unique = true)})
public class Service extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3841799281443671376L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SERVICE_SEQ")
	private long serviceId;
	private String name;
	@OneToMany(mappedBy = "service",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installment> installment = new HashSet<Installment>();
	private Date date;
	@Enumerated(EnumType.ORDINAL)
	ServiceStatus status;

    public enum ServiceStatus {
        PENDING,
        APPROVED,
        NOT_APPROVED;
    }
	
	
}
