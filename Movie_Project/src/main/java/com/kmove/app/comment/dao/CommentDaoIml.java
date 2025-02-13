package com.kmove.app.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kmove.app.comment.vo.CommentVo;
import com.kmove.app.user.vo.BlockUserVo;

@Repository
public class CommentDaoIml implements CommentDao {

	private String namespace = "com.kmove.app.mapper.CommentMapper.";
	
	@Autowired SqlSession session;
	
	@Override
	public String maxno() throws Exception {
		int no = session.selectOne(namespace+"maxno");
		return String.valueOf(no);
	}

	@Override
	public int Insert_Comment(CommentVo cvo) throws Exception {
		int check = session.selectOne(namespace+"IsitOk",cvo);
		
		//조건 >> user가 존재하고, 게시글도 존재할 경우에만 시행.
		if(check==2) {
			return session.insert(namespace+"commitcomment",cvo);
		}
		else {
			return -1;
		}
	}

	@Override
	public List<CommentVo> Select_Comment(BlockUserVo buvo) throws Exception {
		List<CommentVo> lcv = session.selectList(namespace+"selectcomments",buvo);
		return lcv;
	}



}
