package co.kr.service;

import java.util.List;

import co.kr.vo.ReplyVO;
import co.kr.vo.SearchCriteria;

public interface ReplyService {
	//´ñ±Û ¸ñ·Ï Á¶È¸
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	//´ñ±Û ÀÛ¼º
	public void writeReply(ReplyVO vo) throws Exception;
	
	//´ñ±Û »èÁ¦
	public void deleteReply(ReplyVO vo) throws Exception;
	
	//´ñ±Û ¼öÁ¤
	public void updateReply(ReplyVO vo) throws Exception;
	
	//¼±ÅÃµÈ ´ñ±Û Á¶È¸
	public ReplyVO selectReply(int rno) throws Exception;
	
}
