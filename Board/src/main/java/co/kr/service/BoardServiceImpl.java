package co.kr.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import co.kr.dao.BoardDAO;
import co.kr.vo.BoardVO;
import co.kr.vo.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	//개시글 작성
	public void write(BoardVO boardVO) throws Exception{
		dao.write(boardVO);
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

	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}
	
	//게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception{
		dao.update(boardVO);
	}
	
	//게시물 삭제
	@Override
	public void delete(int bno) throws Exception{
		dao.delete(bno);
	}
}
