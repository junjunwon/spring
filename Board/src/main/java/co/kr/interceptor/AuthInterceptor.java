package co.kr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 특정 경로에 접근할 경우 현재 사용자의 로그인 여부를 체크하는 역할을 수행한다.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger=LoggerFactory.getLogger(AuthInterceptor.class);
	
	
	/*
	 * preHandle()메서드는 현재 사용자가 로그인한 상태여부를 확인하고 컨트롤러를 호출할 것인지, 아닌지 결정한다.
	 * 만약 로그인하지 않은 사용자라면 로그인 페이지(/member/login) 페이지로 이동한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession=request.getSession();
		
		if(httpSession.getAttribute("member")==null) {
			logger.info("current user is not logged");
			response.sendRedirect("/member/login");
		}
		
		return true;
	}
	
}
