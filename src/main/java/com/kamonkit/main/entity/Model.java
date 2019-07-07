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
@SequenceGenerator(name = "MODEL_SEQ")
@Entity(name = "MODEL")
@Table(indexes = {@Index(name = "MODEL_idx_modelId_unique", columnList = "modelId", unique = true)})
public class Model extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -267408540062546185L;
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MODEL_SEQ")
	private long modelId;
	private String name;
	@OneToMany(mappedBy = "model",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Motorcycle> motorcycle = new HashSet<Motorcycle>();
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	public Model() {
		super();
	}
	public Model(String name) {
		super();
		this.name = name;
	}
	
}
