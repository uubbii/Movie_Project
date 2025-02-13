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
	 * �Խñ� �ۼ��� �����ϴ� ��Ʈ�ѷ��Դϴ�.
	 * @param rvo �Խñ� �ۼ� ��
	 * @param rattr ���Ƶ�Ʈ�� ���ÿ� ������ �޽���
	 * @param session  �α��� ���� üũ session
	 * @param mid ��ȭ�� �⺻Ű
	 * 
	 * 
	 * @return �Խñ� �ۼ� ����/���п� ���� ��� ������
	 * */
	@RequestMapping(value="/submitPost", method = RequestMethod.POST)
	public String Write_Review(
	        HttpSession session,
	        @ModelAttribute("reviewVo") ReviewVo rvo,
	        @RequestParam(value="mid", required = true) int mid,  // mid�� �Ķ���ͷ� ����
	        RedirectAttributes rattr) throws Exception {

	    System.out.println("����� �ۼ��� ��û�մϴ�.");
	    rvo.setMid(mid);
	    System.out.println(" RVO�� UIDX >>>"+rvo.getUidx());
	    int res = 0;
	    
	    // Uidx�� ���°�� ������������
	    
	    System.out.println("\n �α��� ���� üũ . .");
	    if ((String) session.getAttribute("uidx") == null) {
	    	System.out.println("\n ��α����� ����� �ۼ� �ź�");
	    	  return Err.ErrPage(rattr, "�α����ؾ߸� �ۼ��� �� �ֽ��ϴ�!!"
	    	  		+ "(JSP���� Form�� �Ⱥ��̰��ϰų� ���â ����� �α��� �����ϵ����ϸ� ������??)");
	    }
	    else {	    	
	    	System.out.println("\n �α���, ����� �ۼ� ����");
		    res = reviewservice.Write_Review(rvo, session,mid);
	    }	    

	    if(res==1) {
	    	return "redirect:/detail?id=" + mid;
	    }
	    else {
	    	  return Err.ErrPage(rattr, "�� �ۼ� ����, Mapper,Ȥ�� uidx/ridx Ȯ�ο�û");
	    }
	}
	
	
//	@RequestMapping(value = "/detail", method = RequestMethod.GET)
//	 public String Show_Reviews(
//			 @RequestParam(value = "id", required = true, defaultValue = "-1") Integer movieId,
//			 Model model)throws Exception {		
//		 
//	 
//	 System.out.println("Ȯ�ε� ��ȭ ID: " + movieId + " ���� ���� ���� ��û ��...");
//	 
//	 List<ReviewVo> reviews = reviewservice.Select_Review(movieId);
//
//
//       model.addAttribute("review", reviews); // ��ȭ ���� ����
//   
//       		return "redirect:/detail?id=" + movieId;
//
//}
//	
	


}
