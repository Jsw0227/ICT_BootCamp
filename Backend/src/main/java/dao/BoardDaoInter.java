package dao;

import java.util.List;

import vo.BoardVO;

public interface BoardDaoInter {
	void addBoard(BoardVO vo); // 입력 메서드
	List<BoardVO> listBoard(); // 출력 메서드
	BoardVO detailBoard(int num); // 상세보기 메서드
}
