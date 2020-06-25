package co.kr.dao;

import co.kr.vo.MemberVO;

public interface MemberDAO {
	
	//ȸ������
	public void register(MemberVO vo) throws Exception;
	
	//ȸ������ ����
	public void memberUpdate(MemberVO vo) throws Exception;
	
	//ȸ�� Ż��
	public void memberDelete(MemberVO vo) throws Exception;
	
	//�α���
	public MemberVO login(MemberVO vo) throws Exception;
	
	//���̵� �ߺ�üũ
	public int idChk(MemberVO vo) throws Exception;
}
