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
	
    // ȸ�� ������ ó���ϴ� userjoin��û�Դϴ�.
    @RequestMapping(value = "/user/join")
    public String userjoin(@ModelAttribute UserVo uvo, RedirectAttributes rattr) throws Exception {
    	
    	// Model ��ü�� ������ maxno,uidx�� ����ϰ�
    	// Null�� ���� Model ��ü�� uvo�� �����ϰ�
    	// Service����
    	
        System.out.println("User :" + uvo);
        String maxno = userservice.maxno();
        System.out.println(maxno);
        String uidx = Chaebun.getUno(maxno);

        System.out.println("User�� uidx : " + uidx);
        uvo.setUidx(uidx);
        int res = userservice.userjoin(uvo);
        System.out.println("result >>" + res);
        
        if(res == 1) {
            String msg = "SUCCESS";
            rattr.addAttribute("msg", msg); // ���� �޽��� ����
            return "redirect:/";
        } else {
            return "/home";
        }
    }
	
	
	
}