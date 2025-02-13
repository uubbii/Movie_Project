package com.kmove.app.review.dao;

import java.util.List;

import com.kmove.app.review.vo.ReviewVo;
import com.kmove.app.user.vo.BlockUserVo;


public interface ReviewDao {

    /**
     * ���� ��¥ �������� ����۵� �� ���� ���� ���� ��ȣ�� �����ɴϴ�.
     * RIDX�� ������ 'RYYYYMMDDXXX'�� ���� �����̸�, �� �޼���� ���� ���� ��ȣ ������ ���� ���˴ϴ�.
     * @return ���� ��¥ �������� ��� ������ ���� ����� ��ȣ
     * @throws Exception �����ͺ��̽� ���� �� ����
     */
    public String maxno() throws Exception;
    
    /**
     * ���ο� ����� ������ REVIEW ���̺� �����մϴ�.
     * ��¥�� ��õ, �Ű�� ��� �⺻������ �����˴ϴ�.
     * @param rvo ������ ��� �ִ� ��ü
     * @return ���� ��� (1: ����, 0: ����)
     * @throws Exception �����ͺ��̽� ���� �� ����
     */
    public int commitreview(ReviewVo rvo) throws Exception;
    
    public List<ReviewVo> Select_Reviews(BlockUserVo buvo) throws Exception;
    
    public List<ReviewVo> Select_Recent_Review(BlockUserVo buvo) throws Exception;

    public ReviewVo Select_Review(String ridx) throws Exception;
}
