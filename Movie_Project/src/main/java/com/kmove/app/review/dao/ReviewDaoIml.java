package com.kmove.app.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.vo.BlockUserVo;

@Repository
public class ReviewDaoIml implements ReviewDao{

	private String namespace = "com.kmove.app.mapper.ReviewMapper.";
	
	@Autowired SqlSession session;
	
	
	@Override
	public String maxno() throws Exception {
		int no = session.selectOne(namespace + "maxno");
        String maxno = String.valueOf(no); 
		return maxno;
	}
	@Override
	public int commitreview(ReviewVo rvo) throws Exception {
		// 사용자 등록
		System.out.print("DAO. . .");
        int res = session.insert(namespace + "commitreview", rvo);
        System.out.println("(DAO) /commitreview 결과 >> " + rvo);        
        return res;		
	}
	@Override
	public List<ReviewVo> Select_Reviews(BlockUserVo buvo) throws Exception {
		List<ReviewVo> lvo = session.selectList(namespace + "selectreviews", buvo);
		return lvo;
	}
	@Override
	public List<ReviewVo> Select_Recent_Review(BlockUserVo buvo) throws Exception {
		List<ReviewVo> lvo = session.selectList(namespace + "selectrecentreview",buvo);
		return lvo;
	}
	@Override
	public ReviewVo Select_Review(String ridx) throws Exception {
		session.update(namespace + "selectreview_++",ridx);
	    return session.selectOne(namespace+"selectreview", ridx);
	}


	
}
