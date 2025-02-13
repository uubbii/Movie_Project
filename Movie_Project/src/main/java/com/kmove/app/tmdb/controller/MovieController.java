package com.kmove.app.tmdb.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kmove.app.comm.Err;
import com.kmove.app.comm.Security;
import com.kmove.app.review.service.ReviewService;
import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.tmdb.service.MovieService;
import com.kmove.app.tmdb.vo.MovieResponseVo.MovieVo;
import com.kmove.app.user.vo.UserVo;



@Controller
public class MovieController {

	@Autowired MovieService movieservice;
	@Autowired private ReviewService reviewService;
	
	/**
	 * 해당 컨트롤러의 API요청의 원리는 특정 파라미터값을 넣고 
	 * 보여주는 원리입니다.
	 * 만약 수정해야할 시, Service로 보내는 파라미터를 임의로 수정하면
	 * 정상 작동되지 않습니다.
	 * 
	 * - URL 예시 
	 * http://localhost:8080/show_movies?time_window=week (o)
	 * http://localhost:8080/show_movies?time_window=weeks (x)
	 * 
	 * */
	@Cacheable("main")
	@RequestMapping(value = "/")
	public String Show_Trend_Movies(Model model,HttpSession session) throws Exception {
		
		    model.addAttribute("DAY", movieservice.Request_Movies("day"));
		    model.addAttribute("WEEK", movieservice.Request_Movies("week"));
		    model.addAttribute("UPCOMING",movieservice.Request_Upcoming());
     	    model.addAttribute("RecentReviews",reviewService.Select_Recent_Review(session));
		    return "home";	
	}
	
	/**
	 * 해당 컨트롤러의 API요청의 원리는 영화의 ID값을 받고,
	 * 해당 ID와 관련된 API와 리뷰글을 가져오는 로직입니다.
	 * 
	 * 만약 수정해야할 시, Service로 보내는 파라미터를 임의로 수정하면
	 * 정상 작동되지 않습니다.
	 * @param movieId 영화API검색을 위한 값입니다. model에 사용됩니다. 
	 * @param session 유저들의 글을 검색하기 전, 자신의 Session값을 통해 UIDX를 확인합니다. 해당 처리는 Service에서 이루어집니다.
	 * @param model 1. 영화의 ID를 통한 상세 검색 결과 2. 유저들의 글 모음 
	 * @return model은 총 2개입니다. 영화 API + 유저들의 리뷰글 관련 정보(비로그인은 모두, 로그인 시는 차단 목록에 없는 유저의 글만 가져옴.)
	 * 
	 * */
	 @RequestMapping(value = "/detail", method = RequestMethod.GET)
	 public String Show_Detail_Movie(
			 @RequestParam(value = "id", required = true, defaultValue = "-1") Integer movieId,
			 HttpSession session,			 
			 Model model)throws Exception {
		 
	 if (movieId == null || movieId <= 0) {
		    return Err.ErrPage(model, "URL에 임의의 숫자를 넣지 마세요.");
		}
		 

	 
	 System.out.println("확인된 영화 ID: " + movieId + " 상세 정보 요청 중...");
	 
	 MovieVo movie = movieservice.Request_Detail(movieId);
     List<ReviewVo> reviews = reviewService.Select_Review(session, movieId);
      
	 
        if (movie == null) {	            
            return Err.ErrPage(model, "해당 영화 정보를 찾을 수 없습니다.");
        }
        
      
        
        
        model.addAttribute("movie", movie); // 영화 정보 전달
        model.addAttribute("reviews", reviews); // 관련 리뷰글들 전달
        return "detail"; // 영화 상세 정보 JSP 경로

}
	 
		/**
		 * 해당 컨트롤러의 API요청의 원리는 검색으로부터 입력받은 값을 토대로
		 * 각각 영화명, 배우명에 넣은 후 그 결과를 보여주는 원리입니다.
		 * 만약 수정해야할 시, Service로 보내는 파라미터를 임의로 수정하면
		 * 정상 작동되지 않습니다.
		 * 
		 * @param word 검색명
		 * @param page (너무 많은 결과의 경우에 대한 처리필요, 아직 구현x)
		 * @return 검색명에 따른 결과물 표출
		 * 
		 * - URL 예시 
		 * http://localhost:8080/show_movies?time_window=week (o)
		 * http://localhost:8080/show_movies?time_window=weeks (x)
		 * 
		 * */
	 @RequestMapping(value = "/search", method = RequestMethod.GET)
	 public String Show_Search_Movie(			 
			 @RequestParam(value = "word") String word , Model model) throws Exception {
		 
		    model.addAttribute("title", movieservice.Request_Search_Movies(word,1));
		    model.addAttribute("actor",movieservice.Request_Search_Person(word, 1));

	        return "search"; // 영화 상세 정보 JSP 경로
	 }
	 
	 @RequestMapping(value = "/actorDetail", method = RequestMethod.GET)
	 public String Show_Search_person(			 
			 @RequestParam(value = "id") int id , Model model) throws Exception {		 
		    
		    model.addAttribute("person",movieservice.Request_People_Credits(id));

	        return "actorDetail"; // 영화 상세 정보 JSP 경로
	 }
	 
	 
}
