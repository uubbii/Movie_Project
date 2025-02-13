package com.kmove.app.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kmove.app.review.service.ReviewService;
import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.service.UserService;

@Controller
public class RestController {	
	
	@Autowired ReviewService reviewservice;
	@Autowired UserService userservice;
	  
    /**
     * 비동기로 추천/신고를 처리함.
     * 
     * 반드시 HTML라인에서 데이타ㅓ줄 떄 uidx, targetridx, type, check 필수적으로 받아올 수 있도록
     * 
     * 해당 요청의 목적 >> reaction에 행위를 추가한 후, 게시글에 존재하는 신고/추천을 증감시킨다.
     * 
     * 1차적으로 컨트롤러차원에서 로그인되지 않았으면 메시지 반환하도록
     * @param session 누가
     * @param targetridx 어떤 글을
     * @param type 추천or 신고(1/2)
     * 
     * */
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> reaction(HttpSession session,
	                                    @RequestParam String targetridx,
	                                    @RequestParam int type,
	                                    @RequestParam(required = false) Integer movieId) throws Exception {

	    int res = -1;
	    Map<String, Object> response = new HashMap<String, Object>();

	    // 비로그인 처리
	    if (session.getAttribute("uidx") == null) {
	        System.out.println("\n 비로그인자는 추천/신고 불가");
	        response.put("status", "fail");
	        response.put("message", "No Login");

	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);
	    }

	    // targetridx가 비어 있거나 null일 경우
	    if (targetridx == null || targetridx.isEmpty()) {
	        response.put("status", "error");
	        response.put("message", "Invalid targetridx");
	        
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);
	    }

	    System.out.println("\n 로그인대상, check 확인 .. 1이면 추천, 2면 신고실행할것." + type);
	    res = userservice.reaction((String) session.getAttribute("uidx"), targetridx, type);

	    // -5는 이미 과거에 누른 전적이 있는 경우
	    if (res == -5) {
	        response.put("status", "error");
	        response.put("message", "Already reacted");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    }

	    // 리뷰 업데이트 관련 처리
	    List<ReviewVo> updatedReview = reviewservice.Select_Review(session, movieId);

	    // updatedReview가 null이 아니고 비어있지 않으면
	    if (updatedReview != null && !updatedReview.isEmpty()) {
	        response.put("status", "success");
	        response.put("updatedReviews", updatedReview);
	    } else {
	        response.put("status", "error");
	        response.put("message", "Cannot find reviews");
	    }

	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}





}