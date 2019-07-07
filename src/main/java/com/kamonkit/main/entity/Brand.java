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
@SequenceGenerator(name = "BRAND_SEQ")
@Entity(name = "BRAND")
@Table(indexes = {@Index(name = "BRAND_idx_brandId_unique", columnList = "brandId", unique = true)})
public class Brand extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2476254249012462825L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BRAND_SEQ")
	private long brandId;
	private String name;
	@OneToMany(mappedBy = "brand",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Model> model = new HashSet<Model>();
	
	public Brand() {
		super();
	}
	
	public Brand(String name) {
		super();
		this.name = name;
	}
	public Brand(long brandId, String name) {
		super();
		this.brandId = brandId;
		this.name = name;
	}
	
}
