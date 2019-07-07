package com.kamonkit.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamonkit.main.entity.Color;
@Repository
public interface ColorRepos extends JpaRepository<Color,Long>{

	public Color findByColorIdAndIsDeletedFalse(long id);
	public Color findByNameAndIsDeletedFalse(String name);
	public List<Color> findAllByIsDeletedFalseOrderByNameAsc();
}
