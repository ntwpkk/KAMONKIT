package com.kamonkit.main.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@SequenceGenerator(name = "SUBDISTRICT_SEQ")
@Entity(name = "SUBDISTRICT")
@Table(indexes = {@Index(name = "SUBDISTRICT_idx_subdistrictId_unique", columnList = "subdistrictId", unique = true)})
public class Subdistrict extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6311490672469278725L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SUBDISTRICT_SEQ")
	private long subdistrictId;
	private String name;
	@OneToMany(mappedBy = "subdistrict",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> address = new HashSet<Address>();
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "district_id")
	private District district;
	private long zipcode;
	
	public Subdistrict() {
		super();
	}

	public Subdistrict(long subdistrictId, String name, long zipcode) {
		super();
		this.subdistrictId = subdistrictId;
		this.name = name;
		this.zipcode = zipcode;
	}
	
	
	

}
