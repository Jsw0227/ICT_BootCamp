package kr.co.ictedu.mvc.dao;

import java.util.List;

import kr.co.ictedu.mvc.dto.UpBoardDTO;

public interface UpBoardDatoInter {
	public void upboardAdd(UpBoardDTO vo);
	public List<UpBoardDTO> upboardList();
	public int getTotal();
}
