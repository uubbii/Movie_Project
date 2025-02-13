package com.kmove.app.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kmove.app.comm.Err;
import com.kmove.app.comm.Security;
import com.kmove.app.user.service.UserService;


@Controller


public class LoginController {
	
	@Autowired UserService userservice;
	
	// 로그인을 처리하는 코드입니다.
	@RequestMapping("/login")
	public String login(String uid, String upw, 
			HttpServletRequest request) throws Exception {
		
		System.out.println("Login : ' " + uid + " ' ");
		System.out.println("upw : ' " + upw + " ' ");
									
		
	//   넘겨받은 ID와 PWD를 DB와 판별 후 
	//   존재할경우 해당 유저로 session연결
		if(userservice.isValidUser(uid,upw)) {			
			HttpSession session = request.getSession();				
			userservice.userlogin(session, uid, upw); 
            session.setAttribute("isLoggedIn", true);  
			System.out.println("Login SuCess!!");			
		    return "redirect:/"; 
		}
		else {						
			System.out.println("This User is unavailble . .");
			Err.ErrMsg(request,"아이디 또는 비밀번호가 잘못되었습니다.");
			return "redirect:/";			 			   
		}		
				
	}	
	// 로그아웃을 처리하는 코드입니다.
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
    	Model model) throws Exception {
        HttpSession sessionchk = request.getSession(false);  
        
        // 세션이 존재하고 로그인 상태일 경우 로그아웃 처리
        if (sessionchk != null && sessionchk.getAttribute("isLoggedIn") != null 
                && (Boolean) sessionchk.getAttribute("isLoggedIn")) {
        	
            Security.removeSession(sessionchk);                        
            return "redirect:/";  // 홈 페이지로 리다이렉트
        } else {
            // 로그인되지 않은 상태에서 로그아웃 요청시 에러 메시지 처리
        	System.out.println("로그아웃 실패");            
            return Err.ErrPage(model, "로그인이 안 되어 있어!!");
        }
    }

	
}
