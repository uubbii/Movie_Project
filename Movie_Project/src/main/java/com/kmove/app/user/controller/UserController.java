package com.kmove.app.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmove.app.comm.Chaebun;
import com.kmove.app.user.service.UserService;
import com.kmove.app.user.vo.UserVo;

@Controller
public class UserController {

	
	@Autowired UserService userservice;
	
    // 회원 가입을 처리하는 userjoin요청입니다.
    @RequestMapping(value = "/user/join")
    public String userjoin(@ModelAttribute UserVo uvo, RedirectAttributes rattr) throws Exception {
    	
    	// Model 객체를 받으면 maxno,uidx를 계산하고
    	// Null로 들어온 Model 객체의 uvo를 설정하고
    	// Service에서
    	
        System.out.println("User :" + uvo);
        String maxno = userservice.maxno();
        System.out.println(maxno);
        String uidx = Chaebun.getUno(maxno);

        System.out.println("User의 uidx : " + uidx);
        uvo.setUidx(uidx);
        int res = userservice.userjoin(uvo);
        System.out.println("result >>" + res);
        
        if(res == 1) {
            String msg = "SUCCESS";
            rattr.addAttribute("msg", msg); // 성공 메시지 전달
            return "redirect:/";
        } else {
            return "/home";
        }
    }
	
	
	
}