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
@SequenceGenerator(name = "COLOR_SEQ")
@Entity(name = "COLOR")
@Table(indexes = {@Index(name = "COLOR_idx_colorId_unique", columnList = "colorId", unique = true)})
public class Color extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4605155275054554126L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLOR_SEQ")
	private long colorId;
	private String name;
	@OneToMany(mappedBy = "color",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Motorcycle> motorcycle = new HashSet<Motorcycle>();
	
	public Color() {
		super();
	}
	
	public Color(String name) {
		super();
		this.name = name;
	}
}
