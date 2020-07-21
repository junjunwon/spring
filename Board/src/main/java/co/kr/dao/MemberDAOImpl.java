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
	
	
	//ȸ�� ����
	@Override
	public void register(MemberVO vo) throws Exception{
		sqlSession.insert("memberMapper.register", vo);
	}
	//ȸ������ ����
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		sqlSession.update("memberMapper.memberUpdate", vo);
	}
	
	//ȸ��Ż��
	@Override
	public void memberDelete(MemberVO vo) throws Exception{
		System.out.println("memberDelete of DAO : "+vo);
		sqlSession.delete("memberMapper.memberDelete", vo);
	}
	
	//�α���
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		
		return sqlSession.selectOne("memberMapper.login", vo);
	}
	
	//���̵� �ߺ�üũ
	@Override
	public int idChk(MemberVO vo) throws Exception{
		int result= sqlSession.selectOne("memberMapper.idChk", vo);
		return result;
	}
	
	//�г��� �ߺ�üũ
	@Override
	public int nameChk(MemberVO vo) throws Exception{
		int result=sqlSession.selectOne("memberMapper.nameChk", vo);
		return result;
	}
	
	//���� ����Ű ���� �޼���
	@Override
	public void GetKey(MemberVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("memberMapper.GetKey", vo);
	}
	
	//���� ����Ű Y�� �ٲ��ִ� �޼���
	@Override
	public void alterUserKey(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("alterUserKey vo : "+vo);
		sqlSession.update("memberMapper.alterUserKey", vo);
	}

}
