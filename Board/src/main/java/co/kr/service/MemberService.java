package co.kr.service;

import co.kr.vo.MemberVO;

public interface MemberService {
	
	//ȸ������
	public void register(MemberVO vo) throws Exception;
	
	//ȸ������ ����
	public void memberUpdate(MemberVO vo) throws Exception;
	
	//ȸ��Ż��
	public void memberDelete(MemberVO vo) throws Exception;
	
	//�α���
	public MemberVO login(MemberVO vo) throws Exception;
	
	
	//���̵� �ߺ�üũ
	public int idChk(MemberVO vo) throws Exception;
	
	//�г��� �ߺ�üũ
	public int nameChk(MemberVO vo) throws Exception;
}
