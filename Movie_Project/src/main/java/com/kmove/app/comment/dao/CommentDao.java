package com.kmove.app.comment.dao;

import java.util.List;

import com.kmove.app.comment.vo.CommentVo;
import com.kmove.app.user.vo.BlockUserVo;

public interface CommentDao {


    /**
     * ���� ��¥ �������� ��۵� �� ���� ���� ���� ��ȣ�� �����ɴϴ�.
     * RIDX�� ������ 'CYYYYMMDDXXX'�� ���� �����̸�, �� �޼���� ���� ���� ��ȣ ������ ���� ���˴ϴ�.
     * @return ���� ��¥ �������� ��� ������ ���� ����� ��ȣ
     * @throws Exception �����ͺ��̽� ���� �� ����
     */
    public String maxno() throws Exception;
    
    /**
     * ���ο� ����� ������ comment ���̺� �����մϴ�.
     * ��¥�� �Ű�� ��� �⺻������ �����˴ϴ�.
     * @param rvo ������ ��� �ִ� ��ü
     * @return ���� ��� (1: ����, 0: ����)
     * @throws Exception �����ͺ��̽� ���� �� ����
     */
    public int Insert_Comment(CommentVo cvo) throws Exception;
    
    public List<CommentVo> Select_Comment(BlockUserVo buvo) throws Exception;       

	
}