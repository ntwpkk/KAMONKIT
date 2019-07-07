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
@SequenceGenerator(name = "MOTORCYCLE_SEQ")
@Entity(name = "MOTORCYCLE")
@Table(indexes = {@Index(name = "MOTORCYCLE_idx_motorcycleId_unique", columnList = "motorcycleId", unique = true)})
public class Motorcycle extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6777840522406933327L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MOTORCYCLE_SEQ")
	private long motorcycleId;
	private String registrationNumber;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "province_id")
	private Province registrationProvince;
	private String chassisNumber;
	private String engineNumber;
	private long price,downPayment;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "model_id")
	private Model model;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "color_id")
	private Color  color;
	@OneToMany(mappedBy = "motorcycle",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contract = new HashSet<Contract>();
	
	@Enumerated(EnumType.ORDINAL)
	MotorcycleStatus status;

    public enum MotorcycleStatus {
        IN,
        OUT
        ;
    }
    
    @Enumerated(EnumType.ORDINAL)
    MotorcycleType type;

    public enum MotorcycleType {
        NEW,
        USED
        ;
    }
    public Motorcycle() {
    	super();
    }
    public Motorcycle(String registrationNumber, String chassisNumber, String engineNumber) {
		super();
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.engineNumber = engineNumber;
	}
	
}
