package com.kamonkit.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Tracking;

@Repository
public interface TrackingRepos extends JpaRepository<Tracking,Long>{

	Tracking findByTrackingIdAndIsDeletedFalse(long id);
	
//	List<Tracking> findAllByDate
	
	
	
	
}
