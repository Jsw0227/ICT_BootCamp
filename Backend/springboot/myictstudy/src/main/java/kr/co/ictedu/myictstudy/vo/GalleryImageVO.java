package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("givo")
@Setter
@Getter
public class GalleryImageVO {
	private int galleryid;
	private String imagename;
}
