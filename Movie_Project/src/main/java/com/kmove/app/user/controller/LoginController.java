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
	
	// �α����� ó���ϴ� �ڵ��Դϴ�.
	@RequestMapping("/login")
	public String login(String uid, String upw, 
			HttpServletRequest request) throws Exception {
		
		System.out.println("Login : ' " + uid + " ' ");
		System.out.println("upw : ' " + upw + " ' ");
									
		
	//   �Ѱܹ��� ID�� PWD�� DB�� �Ǻ� �� 
	//   �����Ұ�� �ش� ������ session����
		if(userservice.isValidUser(uid,upw)) {			
			HttpSession session = request.getSession();				
			userservice.userlogin(session, uid, upw); 
            session.setAttribute("isLoggedIn", true);  
			System.out.println("Login SuCess!!");			
		    return "redirect:/"; 
		}
		else {						
			System.out.println("This User is unavailble . .");
			Err.ErrMsg(request,"���̵� �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.");
			return "redirect:/";			 			   
		}		
				
	}	
	// �α׾ƿ��� ó���ϴ� �ڵ��Դϴ�.
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
    	Model model) throws Exception {
        HttpSession sessionchk = request.getSession(false);  
        
        // ������ �����ϰ� �α��� ������ ��� �α׾ƿ� ó��
        if (sessionchk != null && sessionchk.getAttribute("isLoggedIn") != null 
                && (Boolean) sessionchk.getAttribute("isLoggedIn")) {
        	
            Security.removeSession(sessionchk);                        
            return "redirect:/";  // Ȩ �������� �����̷�Ʈ
        } else {
            // �α��ε��� ���� ���¿��� �α׾ƿ� ��û�� ���� �޽��� ó��
        	System.out.println("�α׾ƿ� ����");            
            return Err.ErrPage(model, "�α����� �� �Ǿ� �־�!!");
        }
    }

	
}
