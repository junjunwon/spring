package co.kr.dao;

import java.util.List;
import java.util.Map;

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
	
	//÷������ ���ε�
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sqlSession.insert("boardMapper.insertFile", map);
	}
	
	//÷������ ��ȸ
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception{
		return sqlSession.selectList("boardMapper.selectFileList", bno);
	}
	
	//÷������ �ٿ�ε�
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectFileInfo", map);
	}
	
	//÷������ ����
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardMapper.updateFile", map);
	}
	
	//�Խ��� ��ȸ��
	@Override
	public void boardHit(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardMapper.boardHit", bno);
	}
}
