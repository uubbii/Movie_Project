package com.kmove.app.user.service;

import java.util.List;

import com.kmove.app.user.vo.UserVo;

public interface UserService {

	public int userjoin(UserVo uvo) throws Exception; // ȸ�� ���� ó��
	public String maxno() throws Exception;
	
}
