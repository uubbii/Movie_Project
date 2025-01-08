package com.kmove.app.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kmove.app.user.vo.UserVo;

@Repository
public class UserDaoIml implements UserDao {
	private String namespace = "com.kmove.app.mapper.UserMapper.";

	@Autowired SqlSession session;
		
    @Override
    public String maxno() throws Exception {
		System.out.print("DAO. . .");
        int no = session.selectOne(namespace + "maxno");
        String maxno = String.valueOf(no); 
        return maxno;
    }

	@Override
	public int userjoin(UserVo uvo) throws Exception {
		// 사용자 등록
		System.out.print("DAO. . .");
        int res = session.insert(namespace + "userJoin", uvo);
        System.out.println("(DAO) /userJoin 결과 >> " + uvo);        
        return res;		
	}
	
}
