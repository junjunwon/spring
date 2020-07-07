package co.kr.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.kr.vo.BoardVO;
import co.kr.vo.SearchCriteria;

public interface BoardService {
	//�Խñ� �ۼ�
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception;
	
	//�Խù� ��� ��ȸ
	public List<BoardVO> list(SearchCriteria scri) throws Exception;
	
	//�Խù� �� ����
	public int listCount(SearchCriteria scri) throws Exception;
	
	//�Խù� ��ȸ
	public BoardVO read(int bno) throws Exception;
	
	//�Խù� ����
	public void update(BoardVO boardVO,
						String[] files,
						String[] fileNames,
						MultipartHttpServletRequest mpRequest) throws Exception;
	
	//�Խù� ����
	public void delete(int bno) throws Exception;
	
	//÷������ ��ȸ
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	
	//÷������ �ٿ�ε�
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
}
