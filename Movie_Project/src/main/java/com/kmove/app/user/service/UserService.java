package com.kmove.app.user.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kmove.app.user.vo.BlockUserVo;
import com.kmove.app.user.vo.UserVo;
import com.mysql.cj.Session;

public interface UserService {
    /**
     * 신규 회원 가입을 실행합니다.
     * 총 2개의 데이터를 파라미터로 받습니다.
     * 
     * 1. Uidx생성
     * 2. Upw생성
     * 3. 프로필판별
     * 
     *  모두 시행되어야 DB에 저장할 수 있습니다.
     *  
     * @param uvo 가입 폼에서 넘어오는 정보
     * @param jspprofile 사용자가 업로드한 프로필의 원본명입니다.
     * 

     * @return 가입이 성공하면 1, 실패하면 0을 반환하는 메소드입니다.
     */
	public int userjoin(UserVo uvo,MultipartFile jspprofile) throws Exception;	
    /**
     * user에 저장된 데이터들을 연산 후 채번 생성에 활용하는 메소드입니다.
     * review, comment에서는 사용할 수 없습니다.
     * @return 채번 뒷자리 값
     * @throws Exception 예외 발생 시
     */
	public String maxno() throws Exception;
	public String Bmaxno() throws Exception;
    /**
     * 사용자 ID와 비밀번호로 유효한 사용자인지 확인합니다.
     * @param uid (사용자 ID)
     * @param upw (사용자 비밀번호)
     * @return 유효한 사용자면 true, 그렇지 않으면 false
     * @throws Exception 예외 발생 시
     */ 
	public boolean isValidUser (String uid, String upw) throws Exception;
    /**
     * 유효한 사용자 정보로 로그인을 진행합니다.
     * 해당 메소드는 isValidUser가 판별된 후 사용되어야합니다.
     * @param session 사용자 세션
     * @param uid 사용자 ID
     * @param upw 사용자 비밀번호
     * @return 로그인된 사용자 정보
     * @throws Exception 예외 발생 시
     */
	public UserVo userlogin(HttpSession session,String uid, String upw) throws Exception;
    /**
     * 사용자 ID와 비밀번호를 사용해 기본키를 가져옵니다.
     * @param uid 사용자 ID
     * @param upw 사용자 비밀번호
     * @return 사용자 기본키
     * @throws Exception 예외 발생 시
     */
	public String PrimaryKey(String uid, String upw) throws Exception;
    /**
     * 사용자가 업로드한 파일을 지정된 경로(src.main.webapp.UPLOAD)에 저장하고 고유한 파일명을 반환합니다.
     * @param file 업로드된 이미지(이름뿐만이 아닌 실제 이미지 객체타입으로 넘어옵니다.)
     * @
     * @return 고유한 파일명
     * @throws Exception 예외 발생 시
     */
	public String handleFileUpload(MultipartFile file) throws Exception;	
    /**
     * 현재 유저의 session값을 가져옵니다.
     * session에서 uidx만을 꺼낸 뒤
     * 해당 uidx와 일치하는 값을 Blocked에서 찾은 뒤 반환합니다.
     * @param session 세션값
     *
     * @return 내가 차단한 유저들 목록
     */
	public List<BlockUserVo> getBlockedUser(HttpSession session) throws Exception;
	
	/**
	 * @param uidx 이 사람이
	 * @param targetid 이 글에 대해서
	 * @param type 추천or신고을 진행함. (1 / 2)
	 * @param check 해당 type에 대해서 실행할건지 취소할건지
	 * 
	 * 
	 * */
	public int reaction(String uidx, String targetid,int type)throws Exception;
	
	
	public int updateuppw(UserVo uvo)throws Exception;
	public int updateinfo(UserVo uvo, HttpSession session)throws Exception;
	public int updatephoto(UserVo uvo, MultipartFile jspprofile)throws Exception;
	
	/**
	 * @param ridx 이 사람이
	 * @param targetUidx 이 사람을
	 * @param reason 이러한 이유로 차단하길 요청합니다.
	 * */
	public int blockuser(String ridx,String targetUidx,String reason )throws Exception;
	
	/*아이디 비밀번호 찾기*/
    public String finduid(String umail, String uphone) throws Exception;
    public int findupw(String umail, String uid , String uupw) throws Exception;
   
   /*아이디 이메일 중복 체크*/
    public int youhavebrother(String uid);
	
}
