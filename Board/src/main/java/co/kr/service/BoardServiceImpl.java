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
	
	//���ñ� �ۼ�
	public void write(BoardVO boardVO) throws Exception{
		dao.write(boardVO);
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

	@Override
	public BoardVO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}
	
	//�Խù� ����
	@Override
	public void update(BoardVO boardVO) throws Exception{
		dao.update(boardVO);
	}
	
	//�Խù� ����
	@Override
	public void delete(int bno) throws Exception{
		dao.delete(bno);
	}
}
