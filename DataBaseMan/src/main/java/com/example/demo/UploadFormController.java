package com.example.demo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadFormController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path="/upload", method = RequestMethod.POST)
	public void upload(HttpSession session, 
            		   HttpServletResponse response,
            		   Model model,
            		   UploadForm form,
            		   @RequestParam MultipartFile file,
            		   String name,
            		   String password)
	{
		
		String[] a=name.split(",",0);
		String[] b=password.split(",",0);

		jdbcTemplate.update("insert into user_info(name, password) VALUES (?, ?)", a[0],b[0]);
		
	}

}
