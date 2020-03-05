package com.dokyuportfolio.main.service;

import java.util.List;
import java.util.Map;

import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

public interface MainService {
	
	boolean userSignUserIdDupCheck(String signForm_userId) throws Exception;
	
	boolean userSignUserNameDupCheck(String signForm_userName) throws Exception;
	
	boolean userSignEmailDupCheck(String signForm_email) throws Exception;
	
	boolean userSign(UserVO vo) throws Exception;
	
	Map<String, Object> userLogin(UserVO vo) throws Exception;
	
	List<MenuVO> loadMenuList() throws Exception;
	
}
