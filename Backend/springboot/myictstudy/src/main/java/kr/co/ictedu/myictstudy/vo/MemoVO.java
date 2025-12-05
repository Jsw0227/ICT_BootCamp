//application.properties에서 mybatis.type-aliases-package 
// 지정한 패키지기 때문에
//@Alias어노테이션으로 등록하면 해당 mapper에서 사용할 수 있다.
package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
@Alias("memovo")
@Getter
@Setter
public class MemoVO {
	private int num;
	private String writer;
	private String conts;
	private String mreip;
	private String mdate;
}
/*
CREATE TABLE memo(
num NUMBER CONSTRAINT memo_num_pk PRIMARY key,
writer VARCHAR2(50) NOT null,
conts VARCHAR2(400) NOT null,
mreip VARCHAR2(25),
mdate date
);
CREATE SEQUENCE memo_seq
INCREMENT BY 1 START WITH 1; 
 * 
 * */
 