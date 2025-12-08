package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.myictstudy.vo.GalleryImageVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;

@Mapper
public interface GalleryDao {
	void add(GalleryVO gvo);
	void addImg(List<GalleryImageVO> gvo);
	List<Map<String, Object>> list(Map<String, String> map);
	int totalCount(Map<String, String> map);
}
