package co.kr.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
}
