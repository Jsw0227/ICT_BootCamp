package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("givo")
@Getter
@Setter
public class GalleryImagesVO {
    private int galleryid;
    private String imagename;
}
