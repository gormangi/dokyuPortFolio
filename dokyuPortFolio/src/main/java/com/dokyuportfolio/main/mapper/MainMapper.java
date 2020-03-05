package com.dokyuportfolio.main.mapper;

import java.util.List;

import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

public interface MainMapper {
	
	int selectUserIdCount(String signForm_userId) throws Exception;
	
	int selectUserNameCount(String signForm_userName) throws Exception;
	
	int selectEmailCount(String signForm_email) throws Exception;
	
	int insertUser(UserVO vo) throws Exception;
	
	UserVO selectUserInfoById(UserVO vo) throws Exception;
	
	UserVO selectUserInfoByIdPwd(UserVO vo) throws Exception;
	
	List<MenuVO> selectMenuList() throws Exception;

}
