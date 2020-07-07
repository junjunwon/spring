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
	
	//개시글 작성
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpRequest) throws Exception{
		dao.write(boardVO);
		
		List<Map<String, Object>> list=fileUtils.parseInsertFileInfo(boardVO, mpRequest);
		int size=list.size();
		for(int i=0; i<size; i++) {
			System.out.println(list.get(i));
			dao.insertFile(list.get(i));
		}
		
		/*
		 @Resource로 FileUtils를 사용할 수 있게 추가
		 Map타입의 List타입으로 list라는 이름에 fileUtils.parseInsertFileInfo(boardVO, mpRequest)를 받아온다.
		 for문을 써서 list의 size만큼 넣어주는 이유는 나중에 여러개의 첨부파일을 등록하기 위해서다.
		 */
		
	}
	
	//게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception{
		return dao.list(scri);
	}
	
	//게시물 총 개수
		@Override
		public int listCount(SearchCriteria scri) throws Exception{
			return dao.listCount(scri);
		}

	//게시물 조회
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.boardHit(bno);
		return dao.read(bno);
	}
	
	//게시물 수정
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
	
	//게시물 삭제
	@Override
	public void delete(int bno) throws Exception{
		dao.delete(bno);
	}
	
	//첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(bno);
	}
	
	//첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(map);
	}
	
}
