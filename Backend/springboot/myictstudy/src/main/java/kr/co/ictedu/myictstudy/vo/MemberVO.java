package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("mem")
@Setter
@Getter
public class MemberVO {
	private int num;
	private String userid;
	private String email;
	private String password;
	private String name;
	private String regdate;
}
/*
CREATE TABLE MEMBER (
num NUMBER CONSTRAINT member_num_pk PRIMARY key,
userid VARCHAR2(60) NOT null,
email VARCHAR2(100) NOT null,
PASSWORD VARCHAR2(20) NOT NULL,
NAME VARCHAR2(50) NOT null,
regdate DATE DEFAULT sysdate,
constraint member_userid_uq UNIQUE(userid)
);
CREATE SEQUENCE member_seq 
INCREMENT BY 1 START WITH 1;
*/