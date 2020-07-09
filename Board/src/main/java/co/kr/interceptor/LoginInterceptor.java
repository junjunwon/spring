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
 * �α����� ����ڿ� ���� ������ HttpSession�� ����ó���� ����Ѵ�.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final String MEMBER="member";
	private static final Logger logger=LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	/*
	 * postHandler()�޼ҵ�� httpSession�� ��Ʈ�ѷ����� ������ member�� �����ϰ� �����̷�Ʈ�Ѵ�.
	 * preHandler() �޼ҵ�� ������ �α��� ������ ���� ��� �ʱ�ȭ�ϴ� ������ �����Ѵ�.
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
		//������ �α��� ���� ����
		if(httpSession.getAttribute(MEMBER)!=null) {
			logger.info("clear login data before");
			httpSession.removeAttribute(MEMBER);
		}
		
		return true;
	}
	
}
