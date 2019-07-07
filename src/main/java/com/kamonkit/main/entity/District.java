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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "DISTRICT_SEQ")
@Entity(name = "DISTRICT")
@Table(indexes = {@Index(name = "DISTRICT_idx_districtId_unique", columnList = "districtId", unique = true)})
public class District extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5317285157122625142L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "DISTRICT_SEQ")
	private long districtId;
	private String name;
	@OneToMany(mappedBy = "district",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subdistrict> subdistrict = new HashSet<Subdistrict>();
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "province_id")
	private Province province;
	
	
	

}
