package co.kr.dao;

import java.util.List;
import java.util.Map;

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
	
	//÷������ ���ε�
	public void insertFile(Map<String, Object> map) throws Exception;
	
	//÷������ ��ȸ
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	
	//÷������ �ٿ�ε�
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	//÷������ ����
	public void updateFile(Map<String, Object> map) throws Exception;
	
	//�Խ��� ��ȸ��
	public void boardHit(int bno) throws Exception;
}
