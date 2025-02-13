package com.kmove.app.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.internet.MimeMessage;

import java.util.Map;
import java.util.Random;

@Service
public class EmailService {
    
    private Map<String, String> codeStorage = new ConcurrentHashMap<String,String>();
    private Map<String, LocalDateTime> timestampStorage = new ConcurrentHashMap<String,LocalDateTime>();

    public String generateRandom() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String emailContent = "";

                if ("고객님의 아이디".equals(subject)) {
                    emailContent = "<div style='font-family: Arial, sans-serif; padding: 20px;'>"
                    		+ "<img src='cid:teamLogo' style='width: 150px; height: auto;'/><br>"
                            + "<h2 style='color: #3498db;'>🔍 아이디 찾기</h2>"
                            + "<p>요청하신 아이디 찾기 결과입니다.</p>"
                            + "<p style='font-weight: bold; font-size: 16px;'>아이디: <span style='color: #e74c3c;'>" + text + "</span></p>"
                            + "</div>";
                } else if ("임시 비밀번호".equals(subject)) {
                    emailContent = "<div style='font-family: Arial, sans-serif; padding: 20px;'>"
                    		+ "<img src='cid:teamLogo' style='width: 150px; height: auto;'/><br>"
                            + "<h2 style='color: #e74c3c;'>🔑 임시 비밀번호 발급</h2>"
                            + "<p>요청하신 임시 비밀번호입니다.</p>"
                            + "<p style='font-weight: bold; font-size: 16px;'>임시 비밀번호: <span style='color: #3498db;'>" + text + "</span></p>"
                            + "<p>로그인 후 반드시 비밀번호를 변경해주세요.</p>"
                            + "</div>";
                } else if("인증 메일".equals(subject)) {
                	emailContent = "<div style='font-family: Arial, sans-serif; padding: 20px; text-align: center;'>"
                			+ "<img src='cid:teamLogo' style='width: 150px; height: auto;'/><br>"
                            + "<h2 style='color: #27ae60;'>📩 이메일 인증</h2>"
                            + "<p>아래 인증번호를 입력하여 이메일을 확인해주세요.</p>"
                            + "<p style='font-weight: bold; font-size: 24px; color: #e67e22;'>" + text + "</p>"
                            + "<p>인증번호는 5분 동안만 유효합니다.</p>"
                            + "</div>";
                }else{
                    throw new IllegalArgumentException("잘못된 이메일 유형입니다.");
                }

                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(emailContent, true);
                File logoFile = new File("C:\\Users\\SAMSUNG\\JavaPro\\66.Project\\Movie_Project\\src\\main\\webapp\\resources\\IMG\\moviepedia_22.png");
                helper.addInline("teamLogo", logoFile);

                mailSender.send(message);
                System.out.println(subject + " 이메일 발송 완료!");
                
            }

    public void storeCode(String email, String code) {
        codeStorage.put(email, code);
        timestampStorage.put(email, LocalDateTime.now());
    }

    public boolean isCodeValid(String email, String inputCode) {
        String storedCode = codeStorage.get(email);
        LocalDateTime sentTime = timestampStorage.get(email);

        if (storedCode == null || sentTime == null) {
            return false; // 코드가 없음
        }
        if (!storedCode.equals(inputCode)) {
            return false; // 코드가 일치하지 않음
        }
        if (LocalDateTime.now().isAfter(sentTime.plusMinutes(5))) {
            return false; // 5분 초과
        }
        return true;
    }
}
