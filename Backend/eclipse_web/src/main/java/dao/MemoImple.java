package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import factory.FactoryService;
import vo.MemoVO;

public class MemoImple implements MemoInter{
	
	private static MemoImple memoDao;
	
	private MemoImple() {
		
	}
	
	public static MemoImple getdao() {
		if(memoDao == null) {
			memoDao = new MemoImple();
		}
		return memoDao;
	}
	
	
	@Override
	public List<MemoVO> listMemo() {
		
		return null;
	}

	@Override
	public void addMemo(MemoVO vo) {
		SqlSession ss = FactoryService.getFactory().openSession();
		ss.insert("memo.save",vo);
		ss.commit();
		ss.close();
		
	}



}
