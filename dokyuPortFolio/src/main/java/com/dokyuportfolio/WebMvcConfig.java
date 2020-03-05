package com.dokyuportfolio;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor()).addPathPatterns("/main/dokyuPortFolio.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/admin/menuManagement.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/admin/userManagement.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/admin/boardManagement.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/admin/categoryManagement.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/admin/boardWriteView.do");
		registry.addInterceptor(new Interceptor()).addPathPatterns("/board/boardDetail.do");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
