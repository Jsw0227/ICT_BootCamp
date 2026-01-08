package kr.co.ictedu.myictstudy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import kr.co.ictedu.myictstudy.vo.GalleryImagesVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;
@Mapper
public interface GalleryDao {
	void add(GalleryVO gvo);
	void addImg(List<GalleryImagesVO> gvo);
	//List<GalleryVO> list(Map<String, String> map);
	//select id="list" resultType="map" parameterType="map">
	//resultType="map" 이기 때문에 List<Map<String, Object>> 이어야 한다.
	List<Map<String, Object>> list(Map<String, String> map);
	int totalCount(Map<String, String> map);
//	@Select("select * from gallery where num=#{num}")
//	GalleryVO detail(int num);
//  
	//SQL테스트 후 작업
	/*
	 * SELECT g.num,g.title,g.writer,
TO_CHAR(g.contents) AS contents,
g.hit, g.reip, g.gdate, gc.galleryid ,
gc.imagename FROM
GALLERY g, GALLERYIMAGES gc
WHERE g.num =+ gc.galleryid and g.num=10
	 * */
	@Select("SELECT g.num,g.title,g.writer,\r\n"
			+ "TO_CHAR(g.contents) AS contents,\r\n"
			+ "g.hit, g.reip, g.gdate, gc.galleryid ,\r\n"
			+ "gc.imagename FROM\r\n"
			+ "GALLERY g, GALLERYIMAGES gc\r\n"
			+ "WHERE g.num =+ gc.galleryid and g.num= #{num}")
	List<Map<String,Object>> detail(int num);

	
}





