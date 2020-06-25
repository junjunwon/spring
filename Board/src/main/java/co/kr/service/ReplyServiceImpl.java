package co.kr.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import co.kr.dao.ReplyDAO;
import co.kr.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Inject
	private ReplyDAO dao;
	
	//��� ��ȸ
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.readReply(bno);
	}
	
	//��� �ۼ�
	@Override
	public void writeReply(ReplyVO vo) throws Exception{
		dao.writeReply(vo);
	}
	
	//��� ����
	@Override
	public void deleteReply(ReplyVO vo) throws Exception{
		dao.deleteReply(vo);
	}
	
	//��� ����
	@Override
	public void updateReply(ReplyVO vo) throws Exception{
		dao.updateReply(vo);
	}
	
	//���õ� ��� ��ȸ
	@Override
	public ReplyVO selectReply(int rno) throws Exception{
		 return dao.selectReply(rno);
	}
}
