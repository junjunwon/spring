package co.kr.dao;

import java.util.List;

import co.kr.vo.BoardVO;
import co.kr.vo.SearchCriteria;

public interface BoardDAO {
	
	//�Խñ� �ۼ�
	public void write(BoardVO boardVO) throws Exception;
	
	//�Խù� ��� ��ȸ
	public List<BoardVO> list(SearchCriteria scri) throws Exception;
	
	//�Խù� �� ����
	public int listCount(SearchCriteria scri) throws Exception;
	
	//�Խù� ��ȸ
	public BoardVO read(int bno) throws Exception;
	
	//�Խù� ����
	public void update(BoardVO boardVO) throws Exception;
	
	//�Խù� ����
	public void delete(int bno) throws Exception;
	
	
}
