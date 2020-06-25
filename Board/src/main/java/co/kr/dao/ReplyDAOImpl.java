package co.kr.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import co.kr.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Inject
	SqlSession sqlsession;
	
	
	//��� ��ȸ
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		// TODO Auto-generated method stub
	
		return sqlsession.selectList("replyMapper.readReply", bno);
		
	}

	//��� �ۼ�
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		sqlsession.insert("replyMapper.writeReply", vo);
	}
	
	//��� ����
	@Override
	public void deleteReply(ReplyVO vo) throws Exception{
		sqlsession.delete("replyMapper.deleteReply", vo);
	}
	
	//��� ����
	@Override
	public void updateReply(ReplyVO vo) throws Exception{
		sqlsession.update("replyMapper.updateReply", vo);
	}
	
	//���õ� ��� ��ȸ
	@Override
	public ReplyVO selectReply(int rno) throws Exception{
		return sqlsession.selectOne("replyMapper.selectReply", rno);
	}
	
}
