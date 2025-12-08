package kr.co.ictedu.myictstudy.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("gvo")
@Setter
@Getter
public class GalleryVO {
	private Integer num;
	private String title;
	private String contents;
	private String writer;
	private String reip;
	private int hit;
	private String gdate;
	private List<GalleryImageVO> getimglist;
}
