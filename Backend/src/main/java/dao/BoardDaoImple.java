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
		// <select id="detail" resultType="vo.BoardVO" parameterMap="int">
		SqlSession ss = FactoryService.getFactory().openSession();
		BoardVO vo = ss.selectOne("board.detail", num);
		ss.close();
		return vo;
	}

	@Override
	public List<BoardVO> listBoard() {
		SqlSession ss = FactoryService.getFactory().openSession();
		//namespace.id
		List<BoardVO> blist = ss.selectList("board.list");
		ss.close();
		return blist;
	}

	@Override
	public void delBoard(int num) {
		SqlSession ss = FactoryService.getFactory().openSession();
		//  <delete id="del" parameterType="int">
		ss.delete("board.del",num);
		ss.commit();
		ss.close();
	}

	@Override
	public void upBoard(BoardVO vo) {
		// <update id="up" parameterType="vo.BoardVO">
		SqlSession ss = FactoryService.getFactory().openSession();
		ss.update("board.up", vo);
		ss.commit();
		ss.close();
	}

	

}
