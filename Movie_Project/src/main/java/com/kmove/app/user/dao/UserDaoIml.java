package com.kmove.app.user.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kmove.app.user.vo.BlockUserVo;
import com.kmove.app.user.vo.LoginVo;
import com.kmove.app.user.vo.UserVo;
import java.util.HashMap;
import java.util.Map;


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
    public String Bmaxno() throws Exception {
		System.out.print("DAO. . .");
        int no = session.selectOne(namespace + "Bmaxno");
        String maxno = String.valueOf(no); 
        return maxno;
    }

	@Override
	public int userjoin(UserVo uvo) throws Exception {
		// 사용자 등록
		System.out.print("DAO. . .");
        int res = session.insert(namespace + "userjoin", uvo);
        System.out.println("(DAO) /userjoin 결과 >> " + uvo);        
        return res;		
	}

	@Override
	public int isValidUser (LoginVo lvo) throws Exception {
		System.out.print("DAO. . .");
		int res = session.selectOne(namespace + "isValidUser",lvo);
		System.out.println("dao res>>" + res);
		return res;
	}

	@Override
	public UserVo login(String Key) throws Exception {
		System.out.println("찾는 대상의 기본키 >>" + Key);
		UserVo uvo = session.selectOne(namespace + "login",Key);
		return uvo;
	}

	@Override
	public String PrimaryKey(LoginVo lvo) throws Exception {
		System.out.print("DAO . . .");		
		String key = session.selectOne(namespace + "PrimaryKey",lvo);
		return key;	
	}

	@Override
	public List<BlockUserVo> getBlockedUser(String uidx) throws Exception {
	List<BlockUserVo> BlockList = session.selectList(namespace + "selectBlocked", uidx);
		return BlockList;
	}
	
//	@Override
//	public int updateuppw(UserVo uvo)throws Exception{
//		System.out.println("userdao..");
//		System.out.println("upw>>" + uvo.getUpw() + ",uid>>" + uvo.getUid());
//		int res = session.update(namespace + "useruppw",uvo);
//		System.out.println("res>>" + res);
//		return res;
//	}
	
	@Override
	   public int updateuppw(UserVo uvo)throws Exception{
	      int res = session.update(namespace + "useruppw",uvo);
	      return res;
	   }
	   
	   @Override
	   public int updateinfo(UserVo uvo)throws Exception{
	      int res = session.update(namespace + "userinfo" , uvo);
	      System.out.println("userdao res>>" + res);
	      return res;
	   }
	   
	   @Override
	   public int updatephoto(UserVo uvo)throws Exception{
		   int res = session.update(namespace + "userphoto", uvo);
		   System.out.println("dao upphoto res>>" + res);
		   return res;
	   }

	   
	   
	   
	   // 이하 생성 
	   
	   @Override
	   public boolean isAlreadyReacted(Map<String, Object> params) {
	       
		   
	       int res = session.selectOne(namespace + "isAlreadyReacted", params);
	     System.out.println("reaction 테이블 탐색 . .  " + res + "개 존재"); 
	     
	       return res==1? true:false;
	   }

	   @Override
	   public int reactInsert(Map<String, Object> params) throws Exception {
	       // Reaction 테이블에 사용자의 반응 추가
	       int result = session.insert(namespace + "reactInsert", params);

	       // targetridx의 앞글자에 따라 게시글의 추천/신고 수 증가
	       String uidx = (String) params.get("uidx");
	       String targetridx = (String) params.get("targetridx");
	       Integer type = (Integer) params.get("type");
	       
	       
	       
	       if (targetridx.startsWith("R")) {
	           // 리뷰 게시글일 경우
	           if (type == 1) {
	               // 추천 수 증가
	               result += session.update(namespace + "ReviewGood", targetridx);
	           } else if (type == 2) {
	               // 신고 수 증가
	               result += session.update(namespace + "ReviewReport", targetridx);
	           }
	       } else if (targetridx.startsWith("B")) {
	           // 게시판 글일 경우
	           if (type == 1) {
	               // 추천 수 증가
	               result += session.update(namespace + "BoardGood", targetridx);
	           } else if (type == 2) {
	               // 신고 수 증가
	               result += session.update(namespace + "BoardReport", targetridx);
	           }
	       }
	       return result;
	   }
	   
	   public boolean IsThisMe(BlockUserVo buvo) throws Exception{
		    String targetUserUidx = session.selectOne(namespace + "IsThisMe", buvo);

		    if (targetUserUidx == null || targetUserUidx.equals(buvo.getUidx())) {
		        return true; 
		    }
		    return false;
	   }
	   
	   public int blockuser(BlockUserVo buvo)throws Exception{		   
		   if (IsThisMe(buvo)) {
			   return -5;
		   }
		   else {
			   int res =session.insert(namespace + "blockuser",buvo);
			   return res;			   
		   }		   
	   }
	   
	   @Override
	      public String finduid(String umail, String uphone) throws Exception{
	         Map<String, Object> paramMap = new HashMap<String, Object>();
	          paramMap.put("umail", umail);
	          paramMap.put("uphone", uphone);
	          System.out.println("parammap>>" + paramMap);
	          
	         String uid = session.selectOne(namespace + "finduid" ,paramMap);
	         System.out.println("uid>>" + uid);
	         
	         return uid;
	      }
	      @Override
	      public int findupw(String umail, String uid , String upw) throws Exception{
	         Map<String, Object> paramMap = new HashMap<String, Object>();
	         paramMap.put("umail", umail);
	         paramMap.put("uid", uid);
	         paramMap.put("upw", upw);
	         int res = session.update(namespace + "findupw" , paramMap);
	         System.out.println("dao res>>" + res);
	         
	         return res;
	      }
	      
	      /*아이디 중복체크*/
	      @Override
	      public int youhavebrother(String uid) {
	         int res = session.selectOne(namespace + "youhavebrother", uid);
	         System.out.println("dao res>>" + res);
	         return res;
	      }
	   
	   
	   
}
