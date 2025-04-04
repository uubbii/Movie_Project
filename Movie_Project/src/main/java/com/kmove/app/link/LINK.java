package com.kmove.app.link;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kmove.app.user.vo.UserVo;

@Controller
public class LINK {
	// 기본적으로 실행되는 main 요청입니다.
	
	@Autowired
	LINKServiceIml linkservice;
	
	@RequestMapping(value = "/")
	public String main(Model model , UserVo uvo)throws Exception {
				
		System.out.println("Start Project_CHeck. .  .");
		List<UserVo> ulist =  linkservice.main();
		System.out.println(ulist);
		
		return "/home";
	}	

	@RequestMapping(value = "/userform")
	public String userform()throws Exception {
				
		System.out.println("유저폼으로!!!");		
		
		return "user/userform";
	}
	
	
}
