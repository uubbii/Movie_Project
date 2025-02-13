package com.kmove.app.comment.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmove.app.comm.Chaebun;
import com.kmove.app.comment.dao.CommentDao;
import com.kmove.app.comment.vo.CommentVo;
import com.kmove.app.user.vo.BlockUserVo;

@Service
public class CommentServiceIml implements CommentService {

	@Autowired CommentDao commentdao;

	
	@Override
	public String maxno() throws Exception {
		String maxno = commentdao.maxno();
		return maxno;
	}
	
	@Override
	public int Write_Comment(CommentVo cvo,HttpSession session) throws Exception {
				
		cvo.setCidx(Chaebun.getCno(maxno()));
		cvo.setCreport(0);      
        cvo.setUidx(cvo.getUidx());
        cvo.setMid(cvo.getMid());
        cvo.setCcomment(cvo.getCcomment());
        cvo.setRidx(cvo.getRidx());	        
        System.out.println(cvo.toString());        
        
		int res = commentdao.Insert_Comment(cvo);		
		return res;
	}


	@Override
	public List<CommentVo> Select_Comments(HttpSession session, String ridx) throws Exception {
		
			BlockUserVo buvo = new BlockUserVo();
			String uidx = (String) session.getAttribute("uidx");
			buvo.setUidx(uidx);
			buvo.setTargetUidx(ridx);

			List<CommentVo> lcv = commentdao.Select_Comment(buvo);					   
		    
			return lcv;
	}

}
