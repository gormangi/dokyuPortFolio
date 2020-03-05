package com.dokyuportfolio.main.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.common.SHA256;
import com.dokyuportfolio.main.mapper.MainMapper;
import com.dokyuportfolio.main.service.MainService;
import com.dokyuportfolio.main.vo.UserVO;

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
	private MainMapper mapper;

	/** 유저아이디 중복체크 **/
	@Override
	public boolean userSignUserIdDupCheck(String signForm_userId) throws Exception {
		
		boolean result = false;
		int res = mapper.selectUserIdCount(signForm_userId);
		
		if(res == 0) {
			result = true;
		}
		
		return result;
	}

	/** 유저닉네임 중복체크 **/
	@Override
	public boolean userSignUserNameDupCheck(String signForm_userName) throws Exception {
		
		boolean result = false;
		int res = mapper.selectUserNameCount(signForm_userName);
		
		if(res == 0) {
			result = true;
		}
		
		return result;
	}

	/** 이메일 중복체크 **/
	@Override
	public boolean userSignEmailDupCheck(String signForm_email) throws Exception {
		
		boolean result = false;
		int res = mapper.selectEmailCount(signForm_email);
		
		if(res == 0) {
			result = true;
		}
		
		return result;
	}

	/** 회원가입 **/
	@Override
	public boolean userSign(UserVO vo) throws Exception {
		
		// 유저번호 생성 (UUID)
		vo.setUserSeq(UUID.randomUUID().toString());
		
		// 유저 비밀번호 암호화
		String pwdKey = SHA256.generateKey();
		String pwd = SHA256.getEncrypt(vo.getUserPwd(), pwdKey);
		vo.setUserPwd(pwd);
		vo.setUserPwdKey(pwdKey);
		
		// 유저정보 insert
		int res = mapper.insertUser(vo);
		boolean result = false;
		
		// 유저정보 insert 성공
		if(res > 0) {
			result = true;
		}
		
		return result;
	}

	/** 계정 유효성체크 **/
	@Override
	public Map<String, Object> userLogin(UserVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		boolean resBoolean = false;
		UserVO userInfo = null;
		
		userInfo = mapper.selectUserInfoById(vo);
		if(userInfo != null) {
			String userPwdKey = userInfo.getUserPwdKey();
			String userPwd = SHA256.getEncrypt(vo.getUserPwd(), userPwdKey);
			vo.setUserPwd(userPwd);
			userInfo = mapper.selectUserInfoByIdPwd(vo);
			if(userInfo != null) {
				resBoolean = true;
			}
		}
		
		result.put("result", resBoolean);
		result.put("userInfo", userInfo);
		return result;
	}

	@Override
	public List<MenuVO> loadMenuList() throws Exception {
		return mapper.selectMenuList();
	}
	
}
