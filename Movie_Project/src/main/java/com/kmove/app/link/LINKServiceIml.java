package com.kmove.app.link;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmove.app.user.vo.UserVo;

	

@Service
public class LINKServiceIml implements LINKService{

	
	@Autowired
	LINKDao linkdao;
	
	public List<UserVo> main() throws Exception {
		// TODO Auto-generated method stub
		System.out.print("Servcie OK . . .");
		List<UserVo> ulist = linkdao.main();
		return ulist;
	}

}
