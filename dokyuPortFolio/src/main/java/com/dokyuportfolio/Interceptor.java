package com.dokyuportfolio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dokyuportfolio.main.vo.UserVO;

public class Interceptor extends HandlerInterceptorAdapter{
	
	/**
	 * 컨트롤러 실행 전에 동작함. 리턴값이 true 인 경우 정상동작 , false 인 경우 컨트롤러 진입하지 않음.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}
	
	/**
	 * 컨트롤러 진입 후 view 를 랜더링 하기 전 실행됨. modelAndView 를 통해 전달값등을 조작 할수있음.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("userInfo");
		if(vo != null) {
			modelAndView.addObject("userSeq",vo.getUserSeq());
			modelAndView.addObject("userId",vo.getUserId());
			modelAndView.addObject("userName",vo.getUserName());
			modelAndView.addObject("email",vo.getEmail());
			modelAndView.addObject("userAuth",vo.getUserAuth());
			modelAndView.addObject("useYn",vo.getUseYn());
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 컨트롤러 진입 후 view 까지 랜더링 한 후 최후에 실행됨.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 비동기 요청시 postHandle , afterCompletion 을 건너뛰고 실행됨.
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
