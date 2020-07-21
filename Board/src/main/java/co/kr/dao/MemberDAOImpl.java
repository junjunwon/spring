package co.kr.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import co.kr.service.UserMailSendServiceImpl;
import co.kr.vo.MemberDTO;
import co.kr.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	SqlSession sqlSession;
	
	
	//회원 가입
	@Override
	public void register(MemberVO vo) throws Exception{
		sqlSession.insert("memberMapper.register", vo);
	}
	//회원정보 수정
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		sqlSession.update("memberMapper.memberUpdate", vo);
	}
	
	//회원탈퇴
	@Override
	public void memberDelete(MemberVO vo) throws Exception{
		System.out.println("memberDelete of DAO : "+vo);
		sqlSession.delete("memberMapper.memberDelete", vo);
	}
	
	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		
		return sqlSession.selectOne("memberMapper.login", vo);
	}
	
	//아이디 중복체크
	@Override
	public int idChk(MemberVO vo) throws Exception{
		int result= sqlSession.selectOne("memberMapper.idChk", vo);
		return result;
	}
	
	//닉네임 중복체크
	@Override
	public int nameChk(MemberVO vo) throws Exception{
		int result=sqlSession.selectOne("memberMapper.nameChk", vo);
		return result;
	}
	
	//유저 인증키 생성 메서드
	@Override
	public void GetKey(MemberVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("memberMapper.GetKey", vo);
	}
	
	//유저 인증키 Y로 바꿔주는 메서드
	@Override
	public void alterUserKey(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("alterUserKey vo : "+vo);
		sqlSession.update("memberMapper.alterUserKey", vo);
	}

}
