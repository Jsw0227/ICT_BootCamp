package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import factory.FactoryService;
import vo.BoardVO;

public class BoardDaoImple implements BoardDaoInter {
	private static BoardDaoImple dao;
	
	private BoardDaoImple() {
		
	}
	
	public synchronized static BoardDaoImple getdao() {
		if(dao == null) {
			dao = new BoardDaoImple();
		}
		return dao;	
	}
	
	
	@Override
	public void addBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		SqlSession ss = FactoryService.getFactory().openSession();
		//  <insert id="save" parameterType="vo.BoardVO">
		ss.insert("board.save",vo);
		ss.commit();
		ss.close();
	}

	@Override
	public BoardVO detailBoard(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> listBoard() {
		SqlSession ss = FactoryService.getFactory().openSession();
		//namespace.id
		List<BoardVO> blist = ss.selectList("board.list");
		ss.close();
		return blist;
	}

	

}
