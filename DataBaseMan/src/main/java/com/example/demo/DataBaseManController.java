package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void regi() {
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String register(@PathVariable String filename, MultipartFile multipartFile,Model model,String name) {
		
		String[] text = name.split(",",0);
		
		StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO user_info (name,password) ")
            .append(" VALUES ('")
            .append(filename)
            .append("','")
            .append("test_comment")
            .append("','")
            .append(text[0])
            .append("')");
        jdbcTemplate.execute(sql.toString());
		
		return "register";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String upload(@PathVariable String filename, MultipartFile multipartFile,String formula){
		try {
			
			String[] text = formula.split(",",0);
			
            // アップロードファイルを保存
            File uploadFile = new File(filename);
            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream uploadFileStream =
                    new BufferedOutputStream(new FileOutputStream(uploadFile));
            uploadFileStream.write(bytes);
            uploadFileStream.close();

            /*// ファイルを移動
            String spa = FileSystems.getDefault().getSeparator();
            String postgresPath = "C:\\Program Files\\PostgreSQL\\10\\data\\pdf";

            Path sourcePath = Paths.get("." + spa + filename);
            Path targetPath = Paths.get(postgresPath + spa + filename);
            File targetFile = targetPath.toFile();
            if (targetFile.exists()) targetFile.delete();
            Files.move(sourcePath, targetPath);*/

            // DBへ挿入
            StringBuffer sql = new StringBuffer();
            sql.append(" INSERT INTO ex_data (name,comment,formula) ")
                .append(" VALUES ('")
                .append("test_name")
                .append("','")
                .append("test_comment")
                .append("','")
                .append(text[0])
                .append("')");
            jdbcTemplate.execute(sql.toString());
            
            return "edit";

        } catch (Exception e) {
            // 異常終了時の処理
            e.printStackTrace();
        } catch (Throwable t) {
            // 異常終了時の処理
            t.printStackTrace();
        }
		
		return "edit";
    }
	
	//jdbcTemplate.update("insert into user_info(name, password) VALUES (?, ?)", name, password);
        
	
	@RequestMapping(value="/print_exData")
	public String ex_data_hyoji(Model model) {
		List<DataBaseMan2> exDataList = databaseman2Service.findAllExData();
 		model.addAttribute("exDataList", exDataList);
 		
 		List<DataBaseMan2> exDataId = databaseman2Service.findExDataListByName("Ex_2");
	    model.addAttribute("exDataId", exDataId);
 		
		return "ex_data_hyoji";
	}
	
}
