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
	 * �ش� ��Ʈ�ѷ��� API��û�� ������ Ư�� �Ķ���Ͱ��� �ְ� 
	 * �����ִ� �����Դϴ�.
	 * ���� �����ؾ��� ��, Service�� ������ �Ķ���͸� ���Ƿ� �����ϸ�
	 * ���� �۵����� �ʽ��ϴ�.
	 * 
	 * - URL ���� 
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
	 * �ش� ��Ʈ�ѷ��� API��û�� ������ ��ȭ�� ID���� �ް�,
	 * �ش� ID�� ���õ� API�� ������� �������� �����Դϴ�.
	 * 
	 * ���� �����ؾ��� ��, Service�� ������ �Ķ���͸� ���Ƿ� �����ϸ�
	 * ���� �۵����� �ʽ��ϴ�.
	 * @param movieId ��ȭAPI�˻��� ���� ���Դϴ�. model�� ���˴ϴ�. 
	 * @param session �������� ���� �˻��ϱ� ��, �ڽ��� Session���� ���� UIDX�� Ȯ���մϴ�. �ش� ó���� Service���� �̷�����ϴ�.
	 * @param model 1. ��ȭ�� ID�� ���� �� �˻� ��� 2. �������� �� ���� 
	 * @return model�� �� 2���Դϴ�. ��ȭ API + �������� ����� ���� ����(��α����� ���, �α��� �ô� ���� ��Ͽ� ���� ������ �۸� ������.)
	 * 
	 * */
	 @RequestMapping(value = "/detail", method = RequestMethod.GET)
	 public String Show_Detail_Movie(
			 @RequestParam(value = "id", required = true, defaultValue = "-1") Integer movieId,
			 HttpSession session,			 
			 Model model)throws Exception {
		 
	 if (movieId == null || movieId <= 0) {
		    return Err.ErrPage(model, "URL�� ������ ���ڸ� ���� ������.");
		}
		 

	 
	 System.out.println("Ȯ�ε� ��ȭ ID: " + movieId + " �� ���� ��û ��...");
	 
	 MovieVo movie = movieservice.Request_Detail(movieId);
     List<ReviewVo> reviews = reviewService.Select_Review(session, movieId);
      
	 
        if (movie == null) {	            
            return Err.ErrPage(model, "�ش� ��ȭ ������ ã�� �� �����ϴ�.");
        }
        
      
        
        
        model.addAttribute("movie", movie); // ��ȭ ���� ����
        model.addAttribute("reviews", reviews); // ���� ����۵� ����
        return "detail"; // ��ȭ �� ���� JSP ���

}
	 
		/**
		 * �ش� ��Ʈ�ѷ��� API��û�� ������ �˻����κ��� �Է¹��� ���� ����
		 * ���� ��ȭ��, ���� ���� �� �� ����� �����ִ� �����Դϴ�.
		 * ���� �����ؾ��� ��, Service�� ������ �Ķ���͸� ���Ƿ� �����ϸ�
		 * ���� �۵����� �ʽ��ϴ�.
		 * 
		 * @param word �˻���
		 * @param page (�ʹ� ���� ����� ��쿡 ���� ó���ʿ�, ���� ����x)
		 * @return �˻��� ���� ����� ǥ��
		 * 
		 * - URL ���� 
		 * http://localhost:8080/show_movies?time_window=week (o)
		 * http://localhost:8080/show_movies?time_window=weeks (x)
		 * 
		 * */
	 @RequestMapping(value = "/search", method = RequestMethod.GET)
	 public String Show_Search_Movie(			 
			 @RequestParam(value = "word") String word , Model model) throws Exception {
		 
		    model.addAttribute("title", movieservice.Request_Search_Movies(word,1));
		    model.addAttribute("actor",movieservice.Request_Search_Person(word, 1));

	        return "search"; // ��ȭ �� ���� JSP ���
	 }
	 
	 @RequestMapping(value = "/actorDetail", method = RequestMethod.GET)
	 public String Show_Search_person(			 
			 @RequestParam(value = "id") int id , Model model) throws Exception {		 
		    
		    model.addAttribute("person",movieservice.Request_People_Credits(id));

	        return "actorDetail"; // ��ȭ �� ���� JSP ���
	 }
	 
	 
}
