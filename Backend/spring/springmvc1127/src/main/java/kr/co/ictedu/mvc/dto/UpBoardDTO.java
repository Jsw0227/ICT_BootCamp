package kr.co.ictedu.mvc.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpBoardDTO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private String reip;
	private String bdate;
	private String imgn;
	//spring web 제공해주는 multipart.MultipartFile
	private MultipartFile mfile;
	private int hit;
	private int cnt;
}
