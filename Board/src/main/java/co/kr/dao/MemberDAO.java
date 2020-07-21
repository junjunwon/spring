package co.kr.dao;

import co.kr.vo.MemberDTO;
import co.kr.vo.MemberVO;

public interface MemberDAO {
	
	//회원가입
	public void register(MemberVO vo) throws Exception;
	
	//회원정보 수정
	public void memberUpdate(MemberVO vo) throws Exception;
	
	//회원 탈퇴
	public void memberDelete(MemberVO vo) throws Exception;
	
	//로그인
	public MemberVO login(MemberVO vo) throws Exception;
	
	//아이디 중복체크
	public int idChk(MemberVO vo) throws Exception;
	
	//닉네임 중복체크
	public int nameChk(MemberVO vo) throws Exception;
	
	//유저 인증키 생성 메서드
	public void GetKey(MemberVO vo) throws Exception;
	
	//유저 인증키 Y로 바꿔주는 메서드
	public void alterUserKey(MemberVO vo) throws Exception;
}
