package com.kmove.app.user.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmove.app.comm.EncryptUtils;
import com.kmove.app.comm.Err;
import com.kmove.app.email.EmailService;
import com.kmove.app.review.service.ReviewService;
import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.service.UserService;
import com.kmove.app.user.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	UserService userservice; // UserService를 자동 주입하여 서비스 로직을 호출합니다.
	@Autowired
	ReviewService reviewservice;
	@Autowired
	EmailService emailservice;

	/**
	 * 회원 가입 요청에 응답하는 컨트롤러입니다.
	 * 
	 * @param uvo        회원 폼
	 * @param jspprofile 사용자가 업로드한 이미지(프로필)
	 * @param 리다이렉트와     동시에 전달할 메시지
	 * @return 회원 가입 성공/실패에 따른 결과 페이지
	 * @throws Exception
	 */
	@RequestMapping(value = "user/join")
	public String userjoin(@ModelAttribute UserVo uvo, @RequestParam MultipartFile jspprofile, RedirectAttributes rattr)
			throws Exception {

		// 회원 가입 결과를 담을 변수. 1은 성공, 0은 실패
		int res = userservice.userjoin(uvo, jspprofile);
		System.out.println("회원가입 => " + (res == 1 ? "성공" : "실패"));

		// 회원 가입 성공 시
		if (res == 1) {
			rattr.addFlashAttribute("msg", "회원가입 성공!");
	        return "redirect:/userform"; // userform.jsp로 리다이렉트
		}

		// 회원 가입 실패 시 오류 메시지
		else if (res == 0) {
			return Err.ErrPage(rattr, "다른 유저가 먼저 DB에 저장했거나 Mapper쿼리 문제같아.."); // 실패 페이지로 이동
		}

		// 예기치 않은 오류가 발생한 경우
		else {
			return Err.ErrPage(rattr, "왠진 모르겠는데 3개 이상의 계정이 가입되어버렸는데..? (O_O)"); // 실패 페이지로 이동
		}
	}

	/**
	 * 회원 정보 수정을 요청하는 메서드입니다.
	 * 
	 * @param uvo     수정할 회원 정보
	 * @param request HTTP 요청 객체
	 * @return 수정 완료 후 리다이렉트될 페이지
	 */
	@RequestMapping("Update-password")
	public String updateuppw(@ModelAttribute UserVo uvo, RedirectAttributes rattr) throws Exception {
		System.out.println("upw>>" + uvo.getUpw());
		System.out.println("uid>>" + uvo.getUid());
		int res = userservice.updateuppw(uvo);
		if (res == 1) {
			rattr.addFlashAttribute("msg", "uppw_OK");
			return "redirect:/userupdate";
		} else if (res == 0) {
			return Err.ErrPage(rattr, "뭔가가 달라서 실패했어...");
		} else {
			return Err.ErrPage(rattr, "????혹시 비밀번호나 기본키 겹치는지 확인해줘..");
		}
	}

	/**
	 * 마이페이지(user.info)에서 수행됩니다. session의 Uidx값을 통해서 Blocked DB에 자신이 추가한 차단 대상 목록을
	 * 가져옵니다.
	 * 
	 * @param session 로그인한 사람의 Uidx를 가져옵니다.
	 * 
	 * @param model   목록리스트를 뽑아서 user.info로 넘깁니다.
	 */
	@RequestMapping(value = "/Search_Blocked")
	public String Search_Blocked(HttpSession session, Model model) throws Exception {
		System.out.println("차단유저 목록 . .");
		model.addAttribute("BlockedList", userservice.getBlockedUser(session));

		return "user/userinfo";
	}

	/**
	 * 회원 정보 수정을 요청하는 메서드입니다.
	 * 
	 * @param uvo     수정할 회원 정보
	 * @param request HTTP 요청 객체
	 * @return 수정 완료 후 리다이렉트될 페이지
	 * @throws Exception
	 */
	@RequestMapping("/User_Update-password")
	@ResponseBody
	public String updateuppw(@ModelAttribute UserVo uvo, HttpSession session, RedirectAttributes rattr)
			throws Exception {
		System.out.println("upw>>" + uvo.getUpw());
		System.out.println("uid>>" + uvo.getUid());
		int res = userservice.updateuppw(uvo);
		System.out.println("res>>" + res);
		System.out.println("controller out==");
		String msg = "";
		if (res == 1) {
			rattr.addFlashAttribute("msg", "uppw_OK");
			return "user/userinfo";
		} else {
			rattr.addFlashAttribute("msg", "uppw_ERR");
			return "user/userinfo";
		}
	}

	/**
	 * @param uvo 가져오는 유저임
	 */
	@RequestMapping(value = "/User_Update-info" )
	public String updateinfo(@ModelAttribute UserVo uvo, HttpSession session, RedirectAttributes rattr)
			throws Exception {
		System.out.println("controller uvo>>" + uvo);
		int res = userservice.updateinfo(uvo,session);
		System.out.println("res>>" + res);
		String msg = "";
		if (res == 1) {
			msg = "up_OK";
			rattr.addFlashAttribute("msg", msg);
			return "redirect:/";
		} else {
			msg = "up_ERR";
			rattr.addFlashAttribute("msg", msg);
			return "redirect:/";
		}
	
	}

	@RequestMapping(value="/User_Update-photo")
	public String updatephoto(@ModelAttribute UserVo uvo, @RequestParam MultipartFile jspprofile)
	throws Exception{
		int res = userservice.updatephoto(uvo,jspprofile);
		
		System.out.println("res>>" + res);
		
		if(res == 1) {
			return "redirect:/";
		}else {
			return "redirect:/";
		}
		
	}
	
	
	
	/**
	 * JSP로부터 차단기능을 수행합니다.
	 * session 본인의 uidx 탐색(로그인 안하면 애초에 안보이므로 Session 조건문은 불필요)
	 * mid 차단 수행후 detail?id=.. 이동 수행
	 * ridx 해당글을 갖고온 후, 해당 글의 uidx를  찾아서 차단
	 * reason 어떤 사유로 차단?
	 *
	 * */
	@RequestMapping(value="/Block", method = RequestMethod.POST)
	public String Block(
		HttpSession session,		
		@RequestParam Integer mid,
		@RequestParam String ridx,
		@RequestParam String reason) throws Exception {
		
		
		String uidx = (String) session.getAttribute("uidx") ;		
		int res = userservice.blockuser(uidx, ridx, reason);			   
	    
	    System.out.println(ridx);
	    System.out.println(reason);
	    
	    System.out.println(res);
	    if(res ==-5) {
	    	
	    	return "redirect:/";
	    }
	    else {
	    	return "redirect:/detail?id=" + mid;	
	    }	    

	}
	
	
	
	
	
	
	/**
	    * 회원의 ID를 찾기 위한 요청을 처리하는 메서드입니다.
	    * 
	    * @return ID 찾기 페이지로 리다이렉트
	    * @throws Exception
	    */
	   @RequestMapping(value ="Find_Uid", method = RequestMethod.POST)
	   @ResponseBody
	   
	   public String finduid(String umail, String uphone) throws Exception {
	      System.out.println("아이디찾기 controller");
	      String uid = userservice.finduid(umail, uphone);
	      
	      emailservice.sendEmail(umail, "고객님의 아이디", "아이디는 " + uid + "입니다");
	      
	      

	       if (uid != null) {
	           return "1"; 
	       } else {
	           return "0"; 
	       }
	   }

	   /**
	    * 회원의 비밀번호를 분실했을 때 요청을 처리하는 메서드입니다.
	    * 
	    * @return 비밀번호 찾기 페이지로 리다이렉트
	    * @throws Exception
	    */
	   @RequestMapping(value = "Find_Upw", method = RequestMethod.POST)
	   @ResponseBody
	   public String findupw(String umail, String uid) throws Exception {
	        Random random = new Random();
	        Integer upw = 1000 + random.nextInt(9000);
	        System.out.println("난수 ㅋㅋ" + upw);
	        String uupw = EncryptUtils.encryptMD5(upw.toString());       
	        int res = userservice.findupw(umail, uid , uupw);
	      System.out.println("findupw controller res>>" + res);
	      
	      emailservice.sendEmail(umail, "임시 비밀번호", "임시비밀번호는"+upw+"입니다" );
	      
	      if (res == 1) {
	           return "1";
	       } else {
	           return "0"; 
	       }
	   }
	   
	   //아이디 중복확인
	   @RequestMapping("you_have_brother")
	   @ResponseBody
	   public String youhavebrother(String uid) {
	      System.out.println("uid>>" + uid);
	      int res = userservice.youhavebrother(uid);
	      System.out.println("controller res>>" + res);
	      if(res >= 1) {
	         System.out.println("nono");
	         return "no";
	      }else {
	         System.out.println("good id");
	         return "yes";
	      }
	      
	   }


	
}
