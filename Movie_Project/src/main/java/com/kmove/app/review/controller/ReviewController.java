package com.kmove.app.review.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmove.app.comm.Err;
import com.kmove.app.review.service.ReviewService;
import com.kmove.app.review.vo.ReviewVo;

@Controller
public class ReviewController{
	
	@Autowired ReviewService reviewservice;

	/**
	 * 게시글 작성에 응답하는 컨트롤러입니다.
	 * @param rvo 게시글 작성 폼
	 * @param rattr 리아디렉트와 동시에 전달할 메시지
	 * @param session  로그인 유무 체크 session
	 * @param mid 영화의 기본키
	 * 
	 * 
	 * @return 게시글 작성 성공/실패에 따른 결과 페이지
	 * */
	@RequestMapping(value="/submitPost", method = RequestMethod.POST)
	public String Write_Review(
	        HttpSession session,
	        @ModelAttribute("reviewVo") ReviewVo rvo,
	        @RequestParam(value="mid", required = true) int mid,  // mid를 파라미터로 받음
	        RedirectAttributes rattr) throws Exception {

	    System.out.println("리뷰글 작성을 요청합니다.");
	    rvo.setMid(mid);
	    System.out.println(" RVO의 UIDX >>>"+rvo.getUidx());
	    int res = 0;
	    
	    // Uidx가 없는경우 오류페이지로
	    
	    System.out.println("\n 로그인 유무 체크 . .");
	    if ((String) session.getAttribute("uidx") == null) {
	    	System.out.println("\n 비로그인자 리뷰글 작성 거부");
	    	  return Err.ErrPage(rattr, "로그인해야만 작성할 수 있습니다!!"
	    	  		+ "(JSP에서 Form이 안보이게하거나 모달창 띄워서 로그인 유도하도록하면 좋을듯??)");
	    }
	    else {	    	
	    	System.out.println("\n 로그인, 리뷰글 작성 승인");
		    res = reviewservice.Write_Review(rvo, session,mid);
	    }	    

	    if(res==1) {
	    	return "redirect:/detail?id=" + mid;
	    }
	    else {
	    	  return Err.ErrPage(rattr, "글 작성 실패, Mapper,혹은 uidx/ridx 확인요청");
	    }
	}
	
	
//	@RequestMapping(value = "/detail", method = RequestMethod.GET)
//	 public String Show_Reviews(
//			 @RequestParam(value = "id", required = true, defaultValue = "-1") Integer movieId,
//			 Model model)throws Exception {		
//		 
//	 
//	 System.out.println("확인된 영화 ID: " + movieId + " 관련 리뷰 정보 요청 중...");
//	 
//	 List<ReviewVo> reviews = reviewservice.Select_Review(movieId);
//
//
//       model.addAttribute("review", reviews); // 영화 정보 전달
//   
//       		return "redirect:/detail?id=" + movieId;
//
//}
//	
	


}
