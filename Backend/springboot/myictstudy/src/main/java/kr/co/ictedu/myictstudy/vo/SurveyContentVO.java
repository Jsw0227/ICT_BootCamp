package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("surveyContentvo")
public class SurveyContentVO {
	//@JsonProperty("surveyType")
    private String surveytype;
	
	//@JsonProperty("surveyTitle")
    private String surveytitle;
	
	//@JsonProperty("surveyCnt")
    private Integer surveycnt;
}
/*
 * 

-- Survey 테이블
CREATE TABLE survey (
    num NUMBER CONSTRAINT survey_num_pk PRIMARY KEY,
    code NUMBER,
    sdate DATE,
    sub VARCHAR2(255)
);
CREATE SEQUENCE survey_seq INCREMENT BY 1 START WITH 1;
-- SurveyContent 테이블 (Survey 테이블의 항목들을 저장)
CREATE TABLE surveycontent (
    subcode NUMBER(19) NOT NULL,
    surveycnt NUMBER(10),
    surveytitle VARCHAR2(255),
	surveytype VARCHAR2(255),
    CONSTRAINT surveycontent_surveycontent_FK FOREIGN KEY (subcode) REFERENCES survey(num)  -- 외래 키 제약조건
);


 */