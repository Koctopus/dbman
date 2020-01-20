package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.DataBaseMan;
import com.example.demo.service.DataBaseManService;
import com.example.demo.model.DataBaseMan2;
import com.example.demo.service.DataBaseMan2Service;

@Controller
public class DataBaseManController{
	
	@Autowired
	DataBaseManService databasemanService;
	
	@Autowired
	DataBaseMan2Service databaseman2Service;
	
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
	
	@RequestMapping(value="/print_userinfo")
	public String hyoji(Model model) {
		List<DataBaseMan> userInfoList = databasemanService.findAlluser_infoData();
 		model.addAttribute("userInfoList", userInfoList);
 		
		return "hyoji";
	}
	
	/*@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String exdataget(@RequestParam(value = "ex_name", required = false) String name,@RequestParam(value = "com", required = false) String comment,@RequestParam(value = "fml", required = false) String formula, Model model) {
		return "edit";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)///registerFormAction
	public void exdatapost(@RequestParam(value = "ex_name", required = false) String name,@RequestParam(value = "com", required = false) String comment,@RequestParam(value = "fml", required = false) String formula, Model model) {
		
		jdbcTemplate.update("insert into ex_data(name, comment, formula) VALUES (?, ?, ?)", name, comment, formula);
        
	}*/
	
	@RequestMapping("/confirm")
	public String confirm(@ModelAttribute("ex_name") String name
						 ,@ModelAttribute("ex_name") String comment
						 ,@ModelAttribute("ex_name") String formula) {
		
		jdbcTemplate.update("insert into ex_data(name, comment, formula) VALUES (?, ?, ?)", name, comment, formula);
		
		return "edit";
	}
	
	@RequestMapping(value="/print_exData")
	public String ex_data_hyoji(Model model) {
		List<DataBaseMan2> exDataList = databaseman2Service.findAllExData();
 		model.addAttribute("exDataList", exDataList);
 		
 		List<DataBaseMan2> exDataId = databaseman2Service.findExDataListByName("Ex_2");
	    model.addAttribute("exDataId", exDataId);
 		
		return "ex_data_hyoji";
	}
	
}
