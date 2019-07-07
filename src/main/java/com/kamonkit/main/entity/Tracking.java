package com.kamonkit.main.entity;


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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@SequenceGenerator(name = "TRACKING_SEQ")
@Entity(name = "TRACKING")
@Table(indexes = {@Index(name = "TRACKING_idx_trackingId_unique", columnList = "trackingId", unique = true)})
public class Tracking extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6352416305892024557L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TRACKING_SEQ")
	private long trackingId;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "contract_id")
	private Contract contract;
	
	@Enumerated(EnumType.ORDINAL)
	TrackingStatus status;

    public enum TrackingStatus {
        SUCCESS,
        FAIL
        ;
    }
	private String annotation;
	
	
}
