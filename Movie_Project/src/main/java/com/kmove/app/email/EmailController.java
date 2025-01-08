package com.kmove.app.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {
	

    @Autowired
    private EmailService emailservice;

    @RequestMapping(value= "/send-email" , method=RequestMethod.GET)
    public String sendEmail(@RequestParam String to, Model model) throws Exception {
    		
    		String code = emailservice.generateRandom();
            
            emailservice.sendEmail(to, "인증 메일", "인증 코드는 "+ code +" 입니다.");
            model.addAttribute("message", "이메일이 성공적으로 발송되었습니다!");
        
        return "/home";
    }
}