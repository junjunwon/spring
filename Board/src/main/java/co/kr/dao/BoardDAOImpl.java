package co.kr.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import co.kr.vo.BoardVO;
import co.kr.vo.SearchCriteria;


@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void write(BoardVO boardVO) throws Exception{
		sqlSession.insert("boardMapper.insert", boardVO);
	}
	
	
	//�Խù� ��� ��ȸ
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception{
		return sqlSession.selectList("boardMapper.listPage", scri);
		//boardMapper.xml���� mapper �� namespace�� boardMapper�̰� �� �߿� id�� list�� ���������ͼ� ��ȯ�ض�
	}
	//�Խù� �� ����
		@Override
		public int listCount(SearchCriteria scri) throws Exception{
			return sqlSession.selectOne("boardMapper.listCount", scri);
			//boardMapper.xml���� mapper �� namespace�� boardMapper�̰� �� �߿� id�� list�� ���������ͼ� ��ȯ�ض�
		}
	
	//�Խù� ��ȸ
	@Override
	public BoardVO read(int bno) throws Exception{
		return sqlSession.selectOne("boardMapper.read", bno);
	}
	
	//�Խù� ����
	@Override
	public void update(BoardVO boardVO) throws Exception{
		sqlSession.update("boardMapper.update", boardVO);
	}
	
	//�Խù� ����
	@Override
	public void delete(int bno) throws Exception{
		sqlSession.delete("boardMapper.delete", bno);
	}
}
