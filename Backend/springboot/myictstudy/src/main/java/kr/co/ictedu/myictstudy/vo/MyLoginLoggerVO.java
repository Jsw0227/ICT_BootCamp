package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Alias("lvo")
public class MyLoginLoggerVO {
	private int num;
	private String idn;//접속자 아이디
	private String reip,uagent; // 아이피, 에이전트
	private String sstime, eetime; // 로그인/로그아웃 시간
	private String status; // 상태값 
}








