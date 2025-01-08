package com.kmove.app.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmove.app.comm.EncryptUtils;
import com.kmove.app.user.dao.UserDao;
import com.kmove.app.user.vo.UserVo;

@Service
public class UserServiceIml implements UserService {

	@Autowired UserDao userdao;


	@Override
	public String maxno() throws Exception{
        System.out.print("service . . . ->");
        String maxno = userdao.maxno();
        return maxno;
	}

	@Override
	public int userjoin(UserVo uvo) throws Exception {
		 System.out.println("(Service) /userJoin 실행..." + uvo);
		 
		 	uvo.setUpw(EncryptUtils.encryptMD5(uvo.getUpw()));
	        int res = userdao.userjoin(uvo);
	        System.out.println("(Service) /userJoin 결과 >> " + res);
	        System.out.println("(Service) /userJoin 실행 종료");
	        return res;
	}
	
	
	
}
