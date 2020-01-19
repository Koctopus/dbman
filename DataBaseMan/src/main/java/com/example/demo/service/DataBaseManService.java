package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DataBaseMan;
import com.example.demo.repository.DataBaseManRepository;

@Service
@Transactional
public class DataBaseManService{
	
	@Autowired
	DataBaseManRepository databasemanRepository;
	
	/**
	 * レコードを全件取得する。
	 * @return
	 */
	public List<DataBaseMan> findAllWeatherData(){
		
		return databasemanRepository.findAll();
	}
	
	public List<DataBaseMan> findUserInfoListByName(String name){
		
		return databasemanRepository.findByName(name);
	}
}