package kr.co.ictedu.myictstudy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.myictstudy.dao.GalleryDao;
import kr.co.ictedu.myictstudy.vo.GalleryImagesVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;
import kr.co.ictedu.myictstudy.vo.UpBoardVO;
//jdbc도 autocommit() -> false 만들어서 기본 자동커밋을 해제
// try ~ commit() catch rollback() 
@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;
	//이미지 정보 -> gallery , 이미지이름들 -> galleryimages
	//transcationProcess 메서드 안에서
	// galleryDao.add(gvo);   
	// galleryDao.addImg(gvoList);  를 하나의 단위로 처리 해주기 때문에
	// 만약 중간에 오류가 발생하면 모두 rollack으로 처리하고
	// 오류가 발생하지 않으면 하나의 단위로 commmit
	@Transactional
	public void transcationProcess(GalleryVO gvo, List<GalleryImagesVO> gvoList) {
		galleryDao.add(gvo); // insert 문 
		//이부분에서 오류를 가정하고 테스트를 해본다.
//		if(!gvoList.isEmpty()) {
//			//not null 제약조건을 위반하도록 유도해본다.
//			gvoList.get(0).setImagename(null);
//		}
		galleryDao.addImg(gvoList); // insert all 문
	}
	/*
	 * 	public List<Map<String, Object>>  list(Map<String, String> map) {
		return upBoardDao.list(map);
	}
	public int totalCount(Map<String, String> map) {
		return upBoardDao.totalCount(map);
	}
	 * */
	public List<Map<String, Object>> list(Map<String, String> map) {
		return galleryDao.list(map);
	}

	public int totalCount(Map<String, String> map) {
		return galleryDao.totalCount(map);
	}
//	public GalleryVO detail(int num) {
//		return galleryDao.detail(num);
//	}
	
	public Map<String, Object> detail(int num) {
	    List<Map<String, Object>> rows = galleryDao.detail(num);
	    for (Map.Entry<String, Object> e : rows.get(0).entrySet()) {
			System.out.println(e.getKey() + ":"+ e.getValue());
		}
	    if (rows.isEmpty()) return null;
	    // 첫 번째 행 중복되는 데이터만 따로
	    // rows.get(0).get("NUM")=> resultType="map" 일때 
	    //List<Map<String,Object>> detail(int num); 반환하면 키값은 컬럼의 대문자가 된다 *****
	    Map<String, Object> result = new HashMap<>();
	    result.put("num", rows.get(0).get("NUM"));
	    result.put("title", rows.get(0).get("TITLE"));
	    result.put("writer", rows.get(0).get("WRITER"));
	    // sql문에서 TO_CHAR(g.contents) AS contents
	    result.put("contents", rows.get(0).get("CONTENTS"));
	    result.put("hit", rows.get(0).get("HIT"));
	    result.put("reip", rows.get(0).get("REIP"));
	    result.put("gdate", rows.get(0).get("GDATE"));
	    // 이미지 리스트 따로 모으기
	    List<String> images = new ArrayList<>();
	    for (Map<String, Object> row : rows) {
	        images.add((String) row.get("IMAGENAME"));
	    }
	    result.put("getimglist", images);
	    return result;
	}

}









