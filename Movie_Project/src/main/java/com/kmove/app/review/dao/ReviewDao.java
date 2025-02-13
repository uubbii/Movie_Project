package com.kmove.app.review.dao;

import java.util.List;

import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.vo.BlockUserVo;


public interface ReviewDao {

    /**
     * 오늘 날짜 기준으로 리뷰글들 중 가장 높은 고유 번호를 가져옵니다.
     * RIDX의 형식은 'RYYYYMMDDXXX'와 같은 구조이며, 이 메서드는 다음 고유 번호 생성을 위해 사용됩니다.
     * @return 오늘 날짜 기준으로 사용 가능한 다음 리뷰글 번호
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public String maxno() throws Exception;
    
    /**
     * 새로운 사용자 정보를 REVIEW 테이블에 삽입합니다.
     * 날짜와 추천, 신고는 모두 기본값으로 설정됩니다.
     * @param rvo 정보를 담고 있는 객체
     * @return 삽입 결과 (1: 성공, 0: 실패)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public int commitreview(ReviewVo rvo) throws Exception;
    
    public List<ReviewVo> Select_Reviews(BlockUserVo buvo) throws Exception;
    
    public List<ReviewVo> Select_Recent_Review(BlockUserVo buvo) throws Exception;

    public ReviewVo Select_Review(String ridx) throws Exception;
}
