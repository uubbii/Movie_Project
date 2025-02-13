package com.kmove.app.user.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kmove.app.comm.Chaebun;
import com.kmove.app.comm.EncryptUtils;
import com.kmove.app.comm.Security;
import com.kmove.app.user.dao.UserDao;
import com.kmove.app.user.vo.BlockUserVo;
import com.kmove.app.user.vo.LoginVo;
import com.kmove.app.user.vo.UserVo;

@Service
public class UserServiceIml implements UserService {

	/**
	 * UserDao 인터페이스는 사용자 관련 데이터베이스 작업을 정의합니다.
	 */
    @Autowired
    UserDao userdao;

     
    
    

    @Override
    public String maxno() throws Exception {
        String maxno = userdao.maxno();
        return maxno;
    }

    @Override
    public String Bmaxno() throws Exception {        
        String maxno = userdao.Bmaxno();
        return maxno;
    }

    @Override
    public int userjoin(UserVo uvo,
    		MultipartFile jspprofile) throws Exception {
    	
        System.out.print("(Service) 회원가입 실행..." + uvo);
        System.out.println("User :" + uvo);
        String maxno = maxno();
        System.out.println(maxno);
        String uidx = Chaebun.getUno(maxno);
        System.out.println("User의 uidx : " + uidx);
                
        
        uvo.setUidx(uidx);
        System.out.print("\n\n 기본키 설정 중 . ." + uvo.getUidx());
        
        uvo.setUpw(EncryptUtils.encryptMD5(uvo.getUpw()));
        System.out.print("\n 비밀번호 설정 중 . ." + uvo.getUpw());
        
        if(jspprofile.isEmpty()) {
        	uvo.setUprofile("Default.jsp");
        	uvo.setUsaveprofile("Default.jsp");
        }
        else {
        	uvo.setUprofile(jspprofile.getOriginalFilename());
        	uvo.setUsaveprofile(handleFileUpload(jspprofile));
        }               
        
        System.out.print("\n 프로필 확인 중 . ." + uvo.getUprofile() + "\n");
 
                          
        
        int res = userdao.userjoin(uvo);
        return res;
    }



    @Override
    public boolean isValidUser(String uid, String upw) throws Exception {
        System.out.print("service . . . ->");
        LoginVo lvo = new LoginVo();
        lvo.setUid(uid);
        lvo.setUpw(EncryptUtils.encryptMD5(upw));

        int res = userdao.isValidUser(lvo);

        return res == 1 ? true : false;
    }     
    

    @Override
    public UserVo userlogin(HttpSession session, String uid, String upw) throws Exception {
        // 조건문에서 존재하는 유저임이 판단된 상태에서 Key(uidx)를 찾기에
        // Key(uidx)가 없을 경우는 반드시 에러
        String Key = PrimaryKey(uid, upw);
        UserVo uvo = userdao.login(Key);

        System.out.println("Key >> " + Key);
        System.out.println("uvo >> " + uvo);      
        Security.setSession(session, uvo);

        System.out.println("\n\n"+uvo.getUprofile()+"\n\n");
        System.out.println("\n\n"+uvo.getUsaveprofile()+"\n\n");
        
        System.out.println(uvo.getUpw());
        return uvo;
    }


    @Override
    public String PrimaryKey(String uid, String upw) throws Exception {
        System.out.println("기본 키 불러오는 중 . ..");
        String UPW = EncryptUtils.encryptMD5(upw);

        LoginVo lvo = new LoginVo();
        lvo.setUid(uid);
        lvo.setUpw(UPW);

        String key = userdao.PrimaryKey(lvo);
        System.out.println(key);
        return key;
    }
    
    
    // ※※※※파일 업로드 처리, 해당 부분은 반드시 실제 공유시 링크 바꿔줄 것.※※※※
    // ※※※※파일 업로드 처리, 해당 부분은 반드시 실제 공유시 링크 바꿔줄 것.※※※※
    // ※※※※파일 업로드 처리, 해당 부분은 반드시 실제 공유시 링크 바꿔줄 것.※※※※
            

    public String handleFileUpload(MultipartFile file) throws Exception {
        String saveProfile = Chaebun.getFileName(file.getOriginalFilename());
        String MyPath = "C:\\Users\\SAMSUNG\\JavaPro\\66.Project\\Movie_Project\\src\\main\\webapp\\resources\\UPLOAD";

        // 파일 저장
        File newFile = new File(MyPath, saveProfile);
        file.transferTo(newFile);

        // 파일 경로 확인
        System.out.println("\n "
        		+ "현재 설정된 내 PC 경로 : " + newFile.getAbsolutePath() + 
        		"\n 실제 설정된 내 PC 경로 : " + newFile);

        return saveProfile; 
    }


	@Override
	public List<BlockUserVo> getBlockedUser(HttpSession session) throws Exception {
		
		System.out.println("\n\n" +session.getAttribute("uidx") + "의 차단목록 가져오는중 . . .\n\n");
		List<BlockUserVo> BlockList = userdao.getBlockedUser((String) session.getAttribute("uidx"));
		
		for (BlockUserVo blockedUser : BlockList) {
		    System.out.println(blockedUser);
		}
		return BlockList;
	}
    
//    public int updateuppw(UserVo uvo)throws Exception{
//    	System.out.println("userservice");
//    	System.out.println("upw>>" + uvo.getUpw() + ",uid>>" + uvo.getUid());
//    	uvo.setUpw(EncryptUtils.encryptMD5(uvo.getUpw()));
//        System.out.print("\n 비밀번호 설정 중 . ." + uvo.getUpw());
//    	int res = userdao.updateuppw(uvo);
//    	System.out.println("res>>" + res);
//    	return res;
//    }

    public int updateuppw(UserVo uvo)throws Exception{
        uvo.setUpw(EncryptUtils.encryptMD5(uvo.getUpw()));
        int res = userdao.updateuppw(uvo);
        return res;
     }
     
     public int updateinfo(UserVo uvo,HttpSession session)throws Exception{
    	 session.setAttribute("uphone", uvo.getUphone());
         session.setAttribute("uaddr1", uvo.getUaddr1());
         session.setAttribute("uaddr2", uvo.getUaddr2());
         session.setAttribute("umail", uvo.getUmail());
         session.setAttribute("upost", uvo.getUpost());
           
        int res = userdao.updateinfo(uvo);
        
        
        return res;
     }

     public int updatephoto(UserVo uvo,MultipartFile jspprofile) throws Exception {
    	 
    	 if(jspprofile.isEmpty()) {
         	uvo.setUprofile("Default.jsp");
         	uvo.setUsaveprofile("Default.jsp");
         }
         else {
         	uvo.setUprofile(jspprofile.getOriginalFilename());
         	uvo.setUsaveprofile(handleFileUpload(jspprofile));
         }
    	System.out.println("uvo>>" + uvo);
    	 System.out.println("uprofile>>" + uvo.getUprofile());
    	 System.out.println("usaveprofile>>" + uvo.getUsaveprofile());
    	 int res = userdao.updatephoto(uvo);
    	 
    	 
    	 return res;
     }
     

  // 추천/신고 처리
     public int reaction(String uidx, String targetridx, int type) throws Exception {
    	 int res = 0;

         // DAO로 보내기위한 데이터 전처리
         Map<String, Object> params = new HashMap<String, Object>();
         params.put("uidx", uidx);
         params.put("targetridx", targetridx);
         params.put("type", type);

         // 데이터 확인용 디버깅
         System.out.println("uidx >> " + params.get("uidx"));
         System.out.println("targetridx >> " + params.get("targetridx"));
         System.out.println("type>> " + params.get("type"));
         

         boolean alreadyReacted = userdao.isAlreadyReacted(params); // 이 메서드는 reaction 존재 여부를 반환한다고 가정
         if (alreadyReacted) {
             System.out.println("이미 추천/신고한 대상.");
             return -5;
         }
         
         System.out.println("여기까지 왔다면 이 요청의 대상은 로그인 대상자이고,\n 또한 이 글의 이 버튼을 누른 이력이 없습니다.");         
         
         System.out.println("추천 or 신고을 실행합니다. Reaction 이력에 추가되고 또한 게시글 역시도 증감됩니다.");
         res = userdao.reactInsert(params);


         return res;
     }
          
     public int blockuser(String ridx,String targetUidx,String reason )throws Exception {
    	 	BlockUserVo buvo = new BlockUserVo();
    	 	
            String maxno = Bmaxno();           
            String bidx = Chaebun.getBno(maxno);
            buvo.setBidx(bidx);
            
            buvo.setUidx(ridx);
            buvo.setBreason(reason);
            buvo.setTargetUidx(targetUidx);
            System.out.println(buvo);
            int res =userdao.blockuser(buvo);
    	 return res;
    	 
     }

     public String finduid(String umail, String uphone) throws Exception{
         System.out.println("service umail >" + umail);
         System.out.println("service uphone>>" + uphone);
         String uid = userdao.finduid(umail, uphone);
         return uid;
      }
      //임시비밀번호 설정
      public int findupw(String umail, String uid, String uupw) throws Exception{
       
       int res = userdao.findupw(umail, uid, uupw);
         
         return res;
      }
      
      //아이디 중복 체크
      public int youhavebrother(String uid) {
         int res = userdao.youhavebrother(uid);
         return res;
      }


     
     
     
}