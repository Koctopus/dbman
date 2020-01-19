package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DataBaseMan;

@Repository
public interface DataBaseManRepository extends JpaRepository<DataBaseMan, Integer> {
	
	List<DataBaseMan> findByName(String name);
	
}