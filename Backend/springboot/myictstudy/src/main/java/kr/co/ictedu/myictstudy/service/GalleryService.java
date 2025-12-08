package kr.co.ictedu.myictstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.myictstudy.dao.GalleryDao;
import kr.co.ictedu.myictstudy.vo.GalleryImageVO;
import kr.co.ictedu.myictstudy.vo.GalleryVO;
// jdbc도 autocommit() -> false로 만들어서 기본 자동 커밋을 해제
// 
@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	@Transactional
	public void transcationProcess(GalleryVO gvo, List<GalleryImageVO> gvoList) {
		galleryDao.add(gvo);
		galleryDao.addImg(gvoList);
	}
}
