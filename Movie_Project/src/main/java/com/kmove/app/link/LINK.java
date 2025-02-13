package com.kmove.app.link;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kmove.app.comm.Err;
import com.kmove.app.comment.service.CommentService;
import com.kmove.app.comment.vo.CommentVo;
import com.kmove.app.review.dao.ReviewDao;
import com.kmove.app.review.vo.ReviewVo;



@Controller
public class LINK {
	// 기본적으로 실행되는 main 요청입니다.
	
	// LINK 컨트롤러는 특정 디렉토리에 두기 애매한 경우 몰아넣는 용도입니다.
	// 모든 페이지전환이 여기서 이루어지는 것은 아닙니다.
	
	@Autowired
	LINKServiceIml linkservice;
	@Autowired CommentService commentservice;
	@Autowired ReviewDao reviewdao;
	

	@RequestMapping(value = "/userform")
	public String userform()throws Exception {
				
		System.out.println("유저폼으로!!!");		
		
		return "user/userform";
	}
	
	@RequestMapping(value = "user/error")
	public String error()throws Exception {
				
		System.out.println("ERror 페이지 요청됨!!!");		
		
		return "user/error";
	}	
	
	@RequestMapping(value = "userupdate")
	public String userupdate(HttpServletRequest request, Model model) throws Exception {
	    
	    HttpSession session = request.getSession();
	    
	    // session.getAttribute("isLoggedIn")이 null일 경우, false로 설정
	    Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn"); // 올바른 속성 이름 사용

	    
	    System.out.println(isLoggedIn);
	    if (isLoggedIn != null && isLoggedIn) {
	        System.out.println("진입성공!!");
	        return "user/userinfo";  // 개인정보 수정 페이지로 리다이렉트
	    } else {
	        System.out.println("개인정보 수정창 진입 실패");
	        return Err.ErrPage(model, "로그인을 해야 볼 수 있어!!");
	    }
	}


		
	
	@RequestMapping(value = "comment", method = {RequestMethod.GET, RequestMethod.POST})
	public String detailreview(HttpSession session, HttpServletRequest request, Model model) throws Exception {
			    
		String ridx = request.getParameter("ridx");
		System.out.println("\n\n"+ridx+"\n\n");
		List<CommentVo> lcv = commentservice.Select_Comments(session, ridx);
		ReviewVo rvo = reviewdao.Select_Review(ridx);
		model.addAttribute("comments",lcv);
		
		model.addAttribute("rvo", rvo);
	    String mid = request.getParameter("mid");
	    String mname = request.getParameter("mname");
	    String poster_path = request.getParameter("poster_path");
	    String uidx = (String) session.getAttribute("uidx");
	    // Model에 데이터 추가
	    model.addAttribute("ridx", ridx);
	    model.addAttribute("mname",mname);
	    model.addAttribute("mid", mid);
	    model.addAttribute("poster_path",poster_path);
	    model.addAttribute("uidx",uidx);
		return "review";
	}



	
	}

