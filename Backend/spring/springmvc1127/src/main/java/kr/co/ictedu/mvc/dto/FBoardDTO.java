package kr.co.ictedu.mvc.dto;
/*
 CREATE TABLE fborad(
num NUMBER PRIMARY key, subject VARCHAR2(100) NOT NULL, 
CONTENT CLOB NOT null, 
writer VARCHAR2(50) NOT null, 
reip VARCHAR2(15) NOT null, 
hit NUMBER DEFAULT 0,
pwd VARCHAR2(20),
fdate DATE DEFAULT sysdate

 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FBoardDTO {
	private int num;
	private String subject;
	private String writer;
	private String pwd;
	private String content;
	private int hit;
	private String reip;
	private String fdate;
}
