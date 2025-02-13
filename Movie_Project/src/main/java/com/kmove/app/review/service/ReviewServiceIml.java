package com.kmove.app.review.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmove.app.comm.Chaebun;
import com.kmove.app.comm.Security;
import com.kmove.app.review.dao.ReviewDao;
import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.vo.BlockUserVo;
import com.kmove.app.user.vo.UserVo;

@Service
public class ReviewServiceIml implements ReviewService {
	
	@Autowired ReviewDao reviewdao;
	
    @Override
    public String maxno() throws Exception {
        String maxno = reviewdao.maxno();
        return maxno;
    }
    
    
	@Override
	public int Write_Review(
			ReviewVo rvo,
			HttpSession session,
			int mid) throws Exception {

				rvo.setRidx(Chaebun.getRno(maxno()));
				rvo.setUidx((String) session.getAttribute("uidx"));				
				rvo.setMid(mid);			
				rvo.setRreport(0);
				rvo.setRview(0);	
				rvo.setRgood(0);
			
				int res = reviewdao.commitreview(rvo);
		return res;
	}


	@Override
	public List<ReviewVo> Select_Review( HttpSession session,int mid) throws Exception {

		BlockUserVo buvo = new BlockUserVo();				
		buvo.setUidx(session.getAttribute("uidx") != null ? session.getAttribute("uidx").toString() : "");		
		buvo.setMid(mid);		
		
		List<ReviewVo> lrv =  reviewdao.Select_Review(buvo);
		 
		return lrv;
	}

	@Override
	public List<ReviewVo> Select_Recent_Review(HttpSession session) throws Exception {

		BlockUserVo buvo = new BlockUserVo();				
		buvo.setUidx(session.getAttribute("uidx") != null ? session.getAttribute("uidx").toString() : "");		
		
		List<ReviewVo> lrv =  reviewdao.Select_Recent_Review(buvo);
		
	    
		return lrv;
	}	

	
	
}
