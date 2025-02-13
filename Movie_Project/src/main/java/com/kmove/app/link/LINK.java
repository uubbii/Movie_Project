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
	// �⺻������ ����Ǵ� main ��û�Դϴ�.
	
	// LINK ��Ʈ�ѷ��� Ư�� ���丮�� �α� �ָ��� ��� ���Ƴִ� �뵵�Դϴ�.
	// ��� ��������ȯ�� ���⼭ �̷������ ���� �ƴմϴ�.
	
	@Autowired
	LINKServiceIml linkservice;
	@Autowired CommentService commentservice;
	@Autowired ReviewDao reviewdao;
	

	@RequestMapping(value = "/userform")
	public String userform()throws Exception {
				
		System.out.println("����������!!!");		
		
		return "user/userform";
	}
	
	@RequestMapping(value = "user/error")
	public String error()throws Exception {
				
		System.out.println("ERror ������ ��û��!!!");		
		
		return "user/error";
	}	
	
	@RequestMapping(value = "userupdate")
	public String userupdate(HttpServletRequest request, Model model) throws Exception {
	    
	    HttpSession session = request.getSession();
	    
	    // session.getAttribute("isLoggedIn")�� null�� ���, false�� ����
	    Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn"); // �ùٸ� �Ӽ� �̸� ���

	    
	    System.out.println(isLoggedIn);
	    if (isLoggedIn != null && isLoggedIn) {
	        System.out.println("���Լ���!!");
	        return "user/userinfo";  // �������� ���� �������� �����̷�Ʈ
	    } else {
	        System.out.println("�������� ����â ���� ����");
	        return Err.ErrPage(model, "�α����� �ؾ� �� �� �־�!!");
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
	    // Model�� ������ �߰�
	    model.addAttribute("ridx", ridx);
	    model.addAttribute("mname",mname);
	    model.addAttribute("mid", mid);
	    model.addAttribute("poster_path",poster_path);
	    model.addAttribute("uidx",uidx);
		return "review";
	}



	
	}

