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
@SequenceGenerator(name = "NAMETITLE_SEQ")
@Entity(name = "NAMETITLE")
@Table(indexes = {@Index(name = "NAMETITLE_idx_nameTitleId_unique", columnList = "nameTitleId", unique = true)})
public class NameTitle extends BaseDomain {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2741102001121820312L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "NAMETITLE_SEQ")
	private long nameTitleId;
	private String name;
	@OneToMany(mappedBy = "nameTitle",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Customer> customer = new HashSet<Customer>();
	
	public NameTitle() {}
	public NameTitle(String name) {
		this.name = name;
	}

}
