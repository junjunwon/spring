package co.kr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * Ư�� ��ο� ������ ��� ���� ������� �α��� ���θ� üũ�ϴ� ������ �����Ѵ�.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger=LoggerFactory.getLogger(AuthInterceptor.class);
	
	
	/*
	 * preHandle()�޼���� ���� ����ڰ� �α����� ���¿��θ� Ȯ���ϰ� ��Ʈ�ѷ��� ȣ���� ������, �ƴ��� �����Ѵ�.
	 * ���� �α������� ���� ����ڶ�� �α��� ������(/member/login) �������� �̵��Ѵ�.
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
