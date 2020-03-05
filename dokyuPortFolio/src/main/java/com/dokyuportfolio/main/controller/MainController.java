package com.dokyuportfolio.main.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.service.MainService;
import com.dokyuportfolio.main.vo.UserVO;

@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	/**
	 * 메인화면
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "main/dokyuPortFolio.do")
	public String dokyuPortFolio(HttpServletRequest request , Model model) throws Exception{
		return "dokyuPortfolio/main/main";
	}
	
	/**
	 * 로그인
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userLogin.do")
	public Map<String, Object> userLogin(HttpServletRequest request , UserVO vo) throws Exception{
		Map<String, Object> result = service.userLogin(vo);
		if((boolean)result.get("result")) {
			UserVO userInfo = (UserVO)result.get("userInfo");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
		}
		return result;
	}
	
	/**
	 * 로그아웃
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userLogout.do")
	public boolean userLogout(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		return true;
	}
	
	/**
	 * 회원가입 - 아이디 중복체크
	 * 
	 * @param request
	 * @param signForm_userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userSignUserIdDupCheck.do")
	public boolean userSignUserIdDupCheck(HttpServletRequest request , @RequestParam("signForm_userId") String signForm_userId) throws Exception{
		return service.userSignUserIdDupCheck(signForm_userId);
	}
	
	/**
	 * 회원가입 - 닉네임 중복체크
	 * 
	 * @param request
	 * @param signForm_userName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userSignUserNameDupCheck.do")
	public boolean userSignUserNameDupCheck(HttpServletRequest request , @RequestParam("signForm_userName") String signForm_userName) throws Exception{
		return service.userSignUserNameDupCheck(signForm_userName);
	}
	
	/**
	 * 회원가입 - 이메일 중복체크
	 * 
	 * @param requeset
	 * @param signForm_email
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userSignEmailDupCheck.do")
	public boolean userSignEmailDupCheck(HttpServletRequest requeset , @RequestParam("signForm_email") String signForm_email) throws Exception{
		return service.userSignEmailDupCheck(signForm_email);
	}
	
	/**
	 * 회원가입
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "main/userSign.do")
	public boolean userSign(HttpServletRequest request , UserVO vo) throws Exception{
		boolean result = service.userSign(vo);
		if(result) {
			HttpSession session = request.getSession();
			vo.setUserAuth("1");
			session.setAttribute("userInfo", vo);
		}
		return result;
	}
	
	/**
	 * 메뉴리스트 조회
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/main/loadMenuList.do")
	public List<MenuVO> loadMenuList(HttpServletRequest request) throws Exception{
		return service.loadMenuList();
	}
	
}
