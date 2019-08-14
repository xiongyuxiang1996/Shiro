package com.shiro.Handler;

import com.shiro.Entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public class AccessHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle被调用");
//		System.out.println(request.getRequestURI());
//		// 从session获取当前登陆用户信息
//		Serializable id = SecurityUtils.getSubject().getSession().getId();
//		SessionKey key = new WebSessionKey(id,request,response);
//		Session session = SecurityUtils.getSecurityManager().getSession(key);
//		Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//		SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
//		User user = (User)coll.getPrimaryPrincipal();
//
//		// 从Shiro获取当前认证用户信息
//		User user2 = (User) SecurityUtils.getSubject().getPrincipal();
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
//		System.out.println("afterCompletion被调用");
	}

}
