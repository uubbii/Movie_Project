package com.kmove.app.comment.dao;

import java.util.List;

import com.kmove.app.comment.vo.CommentVo;
import com.kmove.app.user.vo.BlockUserVo;

public interface CommentDao {


    /**
     * 오늘 날짜 기준으로 댓글들 중 가장 높은 고유 번호를 가져옵니다.
     * RIDX의 형식은 'CYYYYMMDDXXX'와 같은 구조이며, 이 메서드는 다음 고유 번호 생성을 위해 사용됩니다.
     * @return 오늘 날짜 기준으로 사용 가능한 다음 리뷰글 번호
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public String maxno() throws Exception;
    
    /**
     * 새로운 사용자 정보를 comment 테이블에 삽입합니다.
     * 날짜와 신고는 모두 기본값으로 설정됩니다.
     * @param rvo 정보를 담고 있는 객체
     * @return 삽입 결과 (1: 성공, 0: 실패)
     * @throws Exception 데이터베이스 오류 시 예외
     */
    public int Insert_Comment(CommentVo cvo) throws Exception;
    
    public List<CommentVo> Select_Comment(BlockUserVo buvo) throws Exception;       

	
}