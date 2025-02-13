package com.kmove.app.review.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.kmove.app.review.vo.ReviewVo;

public interface ReviewService {

	
	 /** 
     * @param ReviewVo를 입력받아 DB에 저장합니다.
     * @return 게시글 작성에 성공하면 1, 실패하면 0 반환.
     */
    public int Write_Review(ReviewVo rvo,HttpSession session,int mid) throws Exception;
    /**
     * @return Review 테이블에 존재하는 리뷰들에 대한 최신값 +1을 꺼내옵니다.(채번생성전용)
     * 
     * */
    public String maxno() throws Exception;
    /** 
     * 특정 영화에 대한 리뷰들을 조회합니다.
     * @param session 현재 사용자의 세션 정보.(자신의 UIDX를 통해서 차단목록에서 제외한 값을 찾은 뒤 반환한다.)
     * @param mid 조회할 영화의 ID.
     * @return 차단한 유저를 제외한 유저들의 리뷰 반환.
     */
    public List<ReviewVo> Select_Reviews( HttpSession session,int mid) throws Exception;
    
    
    
    /** 
     * 최글 올라온 리뷰글 5개만을 조회합니다.
     * @param session 현재 사용자의 세션 정보.(자신의 UIDX를 통해서 차단목록에서 제외한 값을 찾은 뒤 반환한다.)
     * @param mid 조회할 영화의 ID.
     * @return 차단한 유저를 제외한 유저들의 리뷰 반환.
     */
	public List<ReviewVo> Select_Recent_Review(HttpSession session) throws Exception; 
    
    
	
}
