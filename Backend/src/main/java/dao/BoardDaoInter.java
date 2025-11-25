package dao;

import java.util.List;

import vo.BoardVO;

public interface BoardDaoInter {
	void addBoard(BoardVO vo); // 입력 메서드
	List<BoardVO> listBoard(); // 출력 메서드
	BoardVO detailBoard(int num); // 상세보기 메서드
	void delBoard(int num); // 삭제 메서드
	void upBoard(BoardVO vo);
	//delete from board2 where num=1;
}
