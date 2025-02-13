package com.kmove.app.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/send-email", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String sendEmail(@RequestParam String to) {
        try {
            String code = emailService.generateRandom();
            emailService.sendEmail(to, "인증 메일", "인증 코드는 " + code + " 입니다.");
            emailService.storeCode(to, code);

            return "1";  // 성공 시 "1" 반환
        } catch (Exception e) {
            return "0";  // 실패 시 "0" 반환
        }
    }



    @RequestMapping(value = "/verify-code", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String verifyCode(@RequestParam String to, @RequestParam String code) {
        System.out.println("to>>" + to);
        System.out.println("code>>" + code);

        boolean isValid = emailService.isCodeValid(to, code);

        if (isValid) {
            System.out.println("성공");
            return "1";  // 성공 시 "1" 반환
        } else {
            System.out.println("실패");
            return "0";  // 실패 시 "0" 반환
        }
    }

}
