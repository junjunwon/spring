package co.kr.service;

import javax.servlet.http.HttpServletRequest;

import co.kr.vo.MemberVO;

public interface UserMailSendService {
	public String init() throws Exception;
	
	public String getKey(boolean lowerCheck, int size) throws Exception;
	
	public String mailSendWithUserKey(MemberVO vo,
										HttpServletRequest request) throws Exception;
	
	public void keyAlterConfirm(MemberVO vo) throws Exception;
}
