package com.kmove.app.user.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.kmove.app.user.vo.BlockUserVo;
import com.kmove.app.user.vo.LoginVo;
import com.kmove.app.user.vo.UserVo;

/**
 * UserDao 인터페이스는 사용자 관련 데이터베이스 작업을 정의합니다.
 */
public interface UserDao {

    /**
     * 오늘 날짜 기준으로 사용자의 가장 높은 고유 번호를 가져옵니다.
     * UIDX의 형식은 'UYYYYMMDDXXX'와 같은 구조이며, 이 메서드는 다음 고유 번호 생성을 위해 사용됩니다.
     * @return 오늘 날짜 기준으로 사용 가능한 다음 사용자 번호
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public String maxno() throws Exception;
    public String Bmaxno() throws Exception;

    /**
     * 새로운 사용자 정보를 USER 테이블에 삽입합니다.
     * 프로필 정보와 사용자 상태는 기본값으로 설정됩니다.
     * @param uvo 사용자 정보를 담고 있는 객체
     * @return 삽입 결과 (1: 성공, 0: 실패)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public int userjoin(UserVo uvo) throws Exception;

    /**
     * 주어진 UID와 UPW가 유효한 사용자인지 확인합니다.
     * 사용자 상태가 'N' (정상 사용자)인 경우에만 유효합니다.
     * @param lvo 로그인 정보 객체
     * @return 유효 사용자 여부 (1: 유효, 0: 무효)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public int isValidUser(LoginVo lvo) throws Exception;

    /**
     * UIDX를 기준으로 사용자의 정보를 조회합니다.
     * 사용자 상태가 'N' (정상 사용자)인 경우에만 데이터를 반환합니다.
     * @param Key 사용자 식별 키 (UIDX)
     * @return 사용자 정보 객체
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public UserVo login(String Key) throws Exception;

    /**
     * 주어진 UID와 UPW에 해당하는 사용자의 UIDX를 반환합니다.
     * 사용자 상태가 'N' (정상 사용자)인 경우에만 UIDX를 제공합니다.
     * @param lvo 로그인 정보 객체
     * @return 사용자 고유 번호 (UIDX)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public String PrimaryKey(LoginVo lvo) throws Exception;

    /**
     * 사용자 정보를 수정합니다. 해당 파라미터로 넘어온 객체 그대로 
     * 수정을 진행합니다.
     * @param uvo 수정할 사용자 정보를 담고 있는 객체
     * @return 수정 결과 (1: 성공, 0: 실패, 그외는 모두 에러로 처리할 것.)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    
    public List<BlockUserVo> getBlockedUser(String uidx) throws Exception;
    
    
//    public int userupdate(UserVo uvo) throws Exception;
    
    public int updateuppw(UserVo uvo)throws Exception;
	public int updateinfo(UserVo uvo)throws Exception;
	public int updatephoto(UserVo uvo)throws Exception;

	/**
	 * 방금 누른 버튼이 과거에 시행한 적이 있다면 false를 뱉습니다.	
	 * 
	 * @param reaction과 비교할 객체
	 * 
	 * */
	public boolean isAlreadyReacted(Map<String, Object> params) throws Exception;

	    /**
	     * reaction 테이블에 사용자의 추천/신고 정보를 삽입합니다.
	     * @param params - Map 객체로 "uidx", "targetridx", "type" 등의 값이 포함되어 있음.
	     * @return 삽입에 성공한 행의 수 혹은 성공 여부를 나타내는 정수값.
	     * @throws Exception 삽입 중 예외 발생 시 전달.
	     */
	public int reactInsert(Map<String, Object> params) throws Exception;
	
	public int blockuser(BlockUserVo buvo)throws Exception;

	/*아이디 비밀번호 찾기*/
	   public String finduid(String umail, String uphone) throws Exception;
	   public int findupw(String umail, String uid , String uupw) throws Exception;
	   
	   /*아이디 이메일 중복 체크*/
	   public int youhavebrother(String uid);
}