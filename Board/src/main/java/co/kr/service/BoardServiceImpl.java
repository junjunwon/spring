package co.kr.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.kr.dao.BoardDAO;
import co.kr.util.FileUtils;
import co.kr.vo.BoardVO;
import co.kr.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	private BoardDAO dao;
	
	//���ñ� �ۼ�
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception{
		dao.write(boardVO);
		
		List<Map<String, Object>> list=fileUtils.parseInsertFileInfo(boardVO, mpRequest);
		int size=list.size();
		for(int i=0; i<size; i++) {
			System.out.println(list.get(i));
			dao.insertFile(list.get(i));
		}
		
		/*
		 @Resource�� FileUtils�� ����� �� �ְ� �߰�
		 MapŸ���� ListŸ������ list��� �̸��� fileUtils.parseInsertFileInfo(boardVO, mpRequest)�� �޾ƿ´�.
		 for���� �Ἥ list�� size��ŭ �־��ִ� ������ ���߿� �������� ÷�������� ����ϱ� ���ؼ���.
		 */
		
	}
	
	//�Խù� ��� ��ȸ
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception{
		return dao.list(scri);
	}
	
	//�Խù� �� ����
		@Override
		public int listCount(SearchCriteria scri) throws Exception{
			return dao.listCount(scri);
		}

	//�Խù� ��ȸ
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.boardHit(bno);
		return dao.read(bno);
	}
	
	//�Խù� ����
	@Override
	public void update(BoardVO boardVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception {
		// TODO Auto-generated method stub
		dao.update(boardVO);
		
		List<Map<String, Object>> list=fileUtils.parseUpdateFileInfo(boardVO, files, fileNames, mpRequest);
		Map<String, Object> tempMap=null;
		int size=list.size();
		for(int i=0; i<size; i++) {
			tempMap=list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				dao.insertFile(tempMap);
			}else {
				dao.updateFile(tempMap);
			}
		}
	}
	
	//�Խù� ����
	@Override
	public void delete(int bno) throws Exception{
		dao.delete(bno);
	}
	
	//÷������ ��ȸ
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(bno);
	}
	
	//÷������ �ٿ�ε�
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(map);
	}
	
}
