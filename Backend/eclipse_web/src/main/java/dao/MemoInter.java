package dao;

import java.util.List;

import vo.MemoVO;

public interface MemoInter {
	List<MemoVO> listMemo();
	
	void addMemo(MemoVO vo);
}
