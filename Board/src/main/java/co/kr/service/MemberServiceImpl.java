package co.kr.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import co.kr.dao.MemberDAO;
import co.kr.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	MemberDAO dao;
	
	
	//회원 가입
	@Override
	public void register(MemberVO vo) throws Exception{
		dao.register(vo);
	}
	
	//회원정보 수정
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		dao.memberUpdate(vo);
	}
	
	//회원탈퇴
	public void memberDelete(MemberVO vo) throws Exception{
		System.out.println("memberDelete of Service : "+vo);
		dao.memberDelete(vo);
	}
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return dao.login(vo);
	}
	
	//아이디 중복 확인
	@Override
	public int idChk(MemberVO vo) throws Exception{
		
		int result= dao.idChk(vo);
		return result;
	}
	
	//닉네임 중복 확인
	@Override
	public int nameChk(MemberVO vo) throws Exception{
		int result=dao.nameChk(vo);
		return result;
	}
}
