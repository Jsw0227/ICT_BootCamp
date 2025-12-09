package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.myictstudy.vo.GalleryImageVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;

@Mapper
public interface GalleryDao {
	void add(GalleryVO gvo);
	void addImg(List<GalleryImageVO> gvo);
	List<Map<String, Object>> list(Map<String, String> map);
	int totalCount(Map<String, String> map);
	
	@Select("SELECT g.num, g.title, g.writer, TO_CHAR(g.contents) AS contents, \r\n"
			+ "g.hit, g.reip, g.gdate, gc.galleryid, gc.imagename \r\n"
			+ "FROM GALLERY g LEFT JOIN GALLERYIMAGES gc\r\n"
			+ "ON g.num = gc.galleryid WHERE g.num = #{num}")
	List<Map<String,Object>> detail(int num);
}
