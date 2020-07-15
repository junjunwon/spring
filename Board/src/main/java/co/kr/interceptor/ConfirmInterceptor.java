package co.kr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ConfirmInterceptor extends HandlerInterceptorAdapter{
	
	private static final String MEMBER="member";
	private static final Logger logger=LoggerFactory.getLogger(ConfirmInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("==================================");
		// TODO Auto-generated method stub
		HttpSession httpSession=request.getSession();
		System.out.println("httpSession123 : "+httpSession);
		ModelMap modelMap=modelAndView.getModelMap();
		System.out.println("modelMap123 : "+modelMap);
		Object memberVO=modelMap.get("member");
		System.out.println("memberVO123 : "+memberVO);
		
		if(memberVO!=null) {
			logger.info("confirm success");
			httpSession.setAttribute(MEMBER, memberVO);
			response.sendRedirect("/member/memberDelete");
		}
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
