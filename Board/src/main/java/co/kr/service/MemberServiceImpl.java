package co.kr.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import co.kr.dao.MemberDAO;
import co.kr.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	MemberDAO dao;
	
	
	//ȸ�� ����
	@Override
	public void register(MemberVO vo) throws Exception{
		dao.register(vo);
	}
	
	//ȸ������ ����
	@Override
	public void memberUpdate(MemberVO vo) throws Exception{
		dao.memberUpdate(vo);
	}
	
	//ȸ��Ż��
	public void memberDelete(MemberVO vo) throws Exception{
		System.out.println("memberDelete of Service : "+vo);
		dao.memberDelete(vo);
	}
	
	//�α���
	@Override
	public MemberVO login(MemberVO vo) throws Exception{
		return dao.login(vo);
	}
	
	//���̵� �ߺ� Ȯ��
	@Override
	public int idChk(MemberVO vo) throws Exception{
		
		int result= dao.idChk(vo);
		return result;
	}
	
	//�г��� �ߺ� Ȯ��
	@Override
	public int nameChk(MemberVO vo) throws Exception{
		int result=dao.nameChk(vo);
		return result;
	}
}
