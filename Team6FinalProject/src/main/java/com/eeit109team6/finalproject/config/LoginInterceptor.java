package com.eeit109team6.finalproject.config;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//测试拦截器1
@Component
public class LoginInterceptor implements HandlerInterceptor {

	// 进入Handler方法之前执行
	// 可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURI();

		// /project/member/login
//
//
//

//		if ("/Team6FinalProject/".equals(url) ) {
//
//			return true;
//		}
//		if (url.indexOf("/CSS/") > 0 || url.indexOf("/JS/") > 0 || url.indexOf("/Images/") > 0) {
//
//			return true;
//		}
//
//		if ("/Team6FinalProject/".equals(url) || "/Team6FinalProject/jump".equals(url)
//				|| url.indexOf("/member/logout") > 0 || url.indexOf("/member/login") > 0
//				|| url.indexOf("/member/thirdPartyLogin") > 0 
//				|| url.indexOf("/member/registerFacebookOrGoogleMember") > 0 ) {
//
//			return true;
//		}
//
//
//		HttpSession session = request.getSession();
//
//		String username = (String) session.getAttribute("username");
//
		HttpSession session = request.getSession();
		if (url.indexOf("/member/logout") > 0 && session.getAttribute("mem") == null) {

			return false;
		}
//		}else {
//			System.out.println("url.indexOf(\"/member/logout\")" + url.indexOf("/member/logout"));
//			System.out.println("username == null" + username == null);
//	
//			
//		}
//
//
//		response.sendRedirect("http://localhost:8080/Team6FinalProject/");
		return true;
	}

}