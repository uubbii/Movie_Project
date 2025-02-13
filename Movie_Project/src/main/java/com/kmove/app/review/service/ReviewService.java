package com.kmove.app.review.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.kmove.app.review.vo.ReviewVo;

public interface ReviewService {

	
	 /** 
     * @param ReviewVo�� �Է¹޾� DB�� �����մϴ�.
     * @return �Խñ� �ۼ��� �����ϸ� 1, �����ϸ� 0 ��ȯ.
     */
    public int Write_Review(ReviewVo rvo,HttpSession session,int mid) throws Exception;
    /**
     * @return Review ���̺� �����ϴ� ����鿡 ���� �ֽŰ� +1�� �����ɴϴ�.(ä����������)
     * 
     * */
    public String maxno() throws Exception;
    /** 
     * Ư�� ��ȭ�� ���� ������� ��ȸ�մϴ�.
     * @param session ���� ������� ���� ����.(�ڽ��� UIDX�� ���ؼ� ���ܸ�Ͽ��� ������ ���� ã�� �� ��ȯ�Ѵ�.)
     * @param mid ��ȸ�� ��ȭ�� ID.
     * @return ������ ������ ������ �������� ���� ��ȯ.
     */
    public List<ReviewVo> Select_Reviews( HttpSession session,int mid) throws Exception;
    
    
    
    /** 
     * �ֱ� �ö�� ����� 5������ ��ȸ�մϴ�.
     * @param session ���� ������� ���� ����.(�ڽ��� UIDX�� ���ؼ� ���ܸ�Ͽ��� ������ ���� ã�� �� ��ȯ�Ѵ�.)
     * @param mid ��ȸ�� ��ȭ�� ID.
     * @return ������ ������ ������ �������� ���� ��ȯ.
     */
	public List<ReviewVo> Select_Recent_Review(HttpSession session) throws Exception; 
    
    
	
}
