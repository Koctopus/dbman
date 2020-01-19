package com.example.demo;

import java.util.List;
import java.util.Map;

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
public class DataBaseManController {
	
	@Autowired
	DataBaseManService weatherService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String yay(@RequestParam(value = "user_name", required = false) String name,@RequestParam(value = "user_pass", required = false) String password, Model model) {
		return "register";
	}
	@RequestMapping(value="/", method = RequestMethod.POST)///registerFormAction
	public String hello(@RequestParam(value = "user_name", required = false) String name,@RequestParam(value = "user_pass", required = false) String password, Model model) {
		
	    //model.addAttribute("message", name);
		
		jdbcTemplate.update("insert into user_info(name, password) VALUES (?, ?)", name, password);
		
		model.addAttribute("hello", "User Informations."); // Hello World!の表示
 		
		// JDBC
		System.out.println("*** JDBC Start. ***");
		String sql = "select * from user_info";
		List<Map<String, Object>> sqlResultList = jdbcTemplate.queryForList(sql);
		sqlResultList.forEach(s -> {
				System.out.println(s);
		});
		System.out.println("*** JDBC End. ***");
        
		return "register";
	}
	
	@RequestMapping(value="/print")
	public String hyoji(Model model) {
		List<DataBaseMan> userInfoList = weatherService.findAllWeatherData();
 		model.addAttribute("userInfoList", userInfoList);
 		
		return "hyoji";
	}
	
}