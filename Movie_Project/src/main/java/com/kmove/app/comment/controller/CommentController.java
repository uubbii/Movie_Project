package com.kmove.app.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kmove.app.comm.Err;
import com.kmove.app.comment.service.CommentService;
import com.kmove.app.comment.vo.CommentVo;

@Controller
public class CommentController {

	@Autowired CommentService commentservice;
	
	/**
	 * 댓글 작성에 응답하는 컨트롤러입니다.
	 * @param cvo 댓글 작성 폼
	 * @param session  로그인 유무 체크 session, 있을경우 자신의 uidx로써 활용
	 * @param mid 영화의 기본키
	 * 
	 * @return 게시글 작성 성공/실패에 따른 결과 페이지
	 * */
	@RequestMapping(value="/commentwrite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> Write_Comment(
	        HttpSession session,
	        @ModelAttribute("CommentVo") CommentVo cvo) throws Exception {

	    System.out.println("댓글 작성을 요청합니다.");          
	    Map<String, Object> response = new HashMap<String, Object>();
	    int res = 0;

	    // 로그인 유무 체크
	    System.out.println("\n 로그인 유무 체크 . .");
	    if (session.getAttribute("uidx") == null || session.getAttribute("uidx").toString().isEmpty()) {
	        System.out.println("\n 비로그인자 댓글 작성 거부");
	        response.put("status", "error");
	        response.put("message", "Please Login");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);  // 401 상태 코드
	    } else {
	        System.out.println("\n 로그인, 댓글 작성 승인");
	        res = commentservice.Write_Comment(cvo, session);
	    }

	    System.out.println("\n\n 현재 >>"+res + "\n\n");
	    if (res == 1) {
	        // 댓글 작성 성공 시 성공 메시지와 함께 작성된 댓글 데이터를 반환
	        List<CommentVo> updatedComments = commentservice.Select_Comments(session, cvo.getRidx());

	        response.put("status", "success");
	        response.put("message", "성공!");
	        response.put("comments", updatedComments);  // 최신 댓글 목록 추가

	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    } else {
	        // 댓글 작성 실패, 삭제된 글 처리
	        response.put("status", "error");
	        response.put("message", "삭제된 글 입니다.");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);  // 400 상태 코드
	    }
	}

	
	
	
	
	
	
	@RequestMapping(value="/getcomments", method = RequestMethod.POST)
	public String Show_Comment(){ return "";}
			
			
			
	
	
}
