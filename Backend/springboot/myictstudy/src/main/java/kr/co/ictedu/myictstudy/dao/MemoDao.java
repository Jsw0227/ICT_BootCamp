package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
//package.InterfaceName => namespace
//추상메서드의 이름 => id값 
import kr.co.ictedu.myictstudy.vo.MemoVO;

@Mapper
public interface MemoDao {
	void add(MemoVO vo);

	List<MemoVO> list(Map<String, String> map);

	MemoVO detail(int num);

	void del(int num);

	int totalCount();
}
