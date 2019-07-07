package com.kamonkit.main.entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(value = BaseDomainListeners.class)
public abstract class BaseDomain implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5589746270138542667L;

	private Date createdDate;
    
    private String createdBy;
    
	private Date updatedDate;
	
	private String updatedBy;
	
	private boolean isDeleted;

    @Version
    private int version;


    
    
    
    
   

}