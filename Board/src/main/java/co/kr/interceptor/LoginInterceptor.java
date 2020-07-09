package co.kr.interceptor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * 로그인한 사용자에 대한 정보를 HttpSession에 보관처리를 담당한다.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String MEMBER="member";
	private static final Logger logger=LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	/*
	 * postHandler()메소드는 httpSession에 컨트롤러에서 저장한 member를 저장하고 리다이렉트한다.
	 * preHandler() 메소드는 기존의 로그인 정보가 있을 경우 초기화하는 역할을 수행한다.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession=request.getSession();
		ModelMap modelMap=modelAndView.getModelMap();
		Object memberVO=modelMap.get("member");
		
		if(memberVO!=null) {
			logger.info("new login success");
			httpSession.setAttribute(MEMBER, memberVO);
			response.sendRedirect("/board/list");
		}
//		else {
//			logger.info("new login is failed");
//			response.sendRedirect("/member/login");
//		}
		
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession=request.getSession();
		//기존의 로그인 정보 제거
		if(httpSession.getAttribute(MEMBER)!=null) {
			logger.info("clear login data before");
			httpSession.removeAttribute(MEMBER);
		}
		
		return true;
	}
	
}
