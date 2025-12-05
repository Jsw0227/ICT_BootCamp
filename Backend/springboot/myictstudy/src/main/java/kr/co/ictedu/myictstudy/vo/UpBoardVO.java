package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
@Alias("upvo")
@Getter
@Setter
public class UpBoardVO {
	private int num;
	private String title;
	private String writer;
	private String content;
	private String imgn;
	private int hit;
	private String reip;
	private String bdate;
	//Post방식의 multipart/form-data 즉 파일 업로드 일때 데이터가 저장 
	private MultipartFile mfile;
}
/*
 CREATE TABLE UPBOARD (
    NUM NUMBER PRIMARY KEY, -- 기본 키
    TITLE VARCHAR2(255) NOT NULL, -- 제목
    WRITER VARCHAR2(100) NOT NULL, -- 작성자
    CONTENT CLOB, -- 내용
    IMGN VARCHAR2(255), -- 이미지 파일명
    HIT NUMBER DEFAULT 0, -- 조회수, 기본값 0
    REIP VARCHAR2(50), -- 등록 IP
    BDATE DATE DEFAULT SYSDATE -- 등록 날짜, 기본값 현재 날짜
);
CREATE SEQUENCE UPBOARD_SEQ
INCREMENT BY 1  START WITH 1; 
 * */
 