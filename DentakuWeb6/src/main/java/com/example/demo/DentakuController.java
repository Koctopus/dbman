package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DataBaseMan;
import com.example.demo.service.DataBaseManService;

@Controller
public class DentakuController{
	
	@Autowired
	DataBaseManService databasemanService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(){
		return "edit";
	}
	
	@RequestMapping(value = "/drop")
	public String drop(){
		return "drop";
	}
	
	@RequestMapping(value = "/learn")
	public String learn(){
		return "learn";
	}
	
	@RequestMapping(value = "/graph")
	public String graph(){
		return "graph";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String yay(@RequestParam(value = "user_name", required = false) String name,@RequestParam(value = "user_pass", required = false) String password, Model model) {
		return "register";
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)///registerFormAction
	public String hello(@RequestParam(value = "user_name", required = false) String name,@RequestParam(value = "user_pass", required = false) String password, Model model) {
		
		jdbcTemplate.update("insert into user_info(name, password) VALUES (?, ?)", name, password);
        
		return "register";
	}
	
	@RequestMapping(value="/print")
	public String hyoji(Model model) {
		List<DataBaseMan> userInfoList = databasemanService.findAllWeatherData();
 		model.addAttribute("userInfoList", userInfoList);
 		
		return "hyoji";
	}
	
}
