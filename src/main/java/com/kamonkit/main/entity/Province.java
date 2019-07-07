package com.kamonkit.main.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@SequenceGenerator(name = "PROVINCE_SEQ")
@Entity(name = "PROVINCE")
@Table(indexes = {@Index(name = "PROVINCE_idx_provinceId_unique", columnList = "provinceId", unique = true)})
public class Province extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1959546495940456169L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PROVINCE_SEQ")
	private long provinceId;
	private String name;
	@OneToMany(mappedBy = "registrationProvince",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Motorcycle> motorcycle = new HashSet<Motorcycle>();
	@OneToMany(mappedBy = "province",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<District> district = new HashSet<District>();
	
	
}
