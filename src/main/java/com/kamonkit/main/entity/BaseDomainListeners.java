package com.kamonkit.main.entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Date;

public class BaseDomainListeners <T extends BaseDomain> {

    @PrePersist
    private void prePersist(T e) {
        e.setCreatedDate(new Date());
        e.setDeleted(false);
    }

    @PreUpdate
    private void preUpdate(T e) {
        e.setUpdatedDate(new Date());
    }



}
