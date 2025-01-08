package com.kmove.app.user.dao;

import java.util.List;

import com.kmove.app.user.vo.UserVo;

public interface UserDao {
	public String maxno() throws Exception;
	public int userjoin(UserVo uvo)throws Exception;
}
