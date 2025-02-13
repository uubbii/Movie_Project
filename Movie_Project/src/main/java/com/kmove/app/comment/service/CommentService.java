package com.kmove.app.comment.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.kmove.app.comment.vo.CommentVo;

public interface CommentService {

	
	 /** 
	  * rvo CommentVo�� �Է¹޾� DB�� �����մϴ�. 
       @param Session uidx ������ 
     * @param rvo ReviewVo�� �����մϴ�.  
     * @return ��� �ۼ��� �����ϸ� 1, �����ϸ� 0 ��ȯ.
     */
	public int Write_Comment(CommentVo cvo,HttpSession session) throws Exception;
    
    /**
     * @return Comment ���̺� �����ϴ� ����鿡 ���� �ֽŰ� +1�� �����ɴϴ�.(ä����������)
     * 
     * */
    public String maxno() throws Exception;
    
    
    /** 
     * Ư�� ��ȭ�� ���� ������� ��ȸ�մϴ�.
     * @param session ���� ������� ���� ����.(�ڽ��� UIDX�� ���ؼ� ���ܸ�Ͽ��� ������ ���� ã�� �� ��ȯ�Ѵ�.)
     * @param ridx ��ȸ�� �Խñ��� ridx
     * @return ������ ������ ������ �������� ��� ��ȯ.
     */
    public List<CommentVo> Select_Comments( HttpSession session,String ridx) throws Exception;
    
}
