package com.shiro.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootConfiguration
public class AccessMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Autowired
	AccessHandlerInterceptor accessHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截“/login”以外的所有访问
		registry.addInterceptor(accessHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
		super.addInterceptors(registry);
	}
}
