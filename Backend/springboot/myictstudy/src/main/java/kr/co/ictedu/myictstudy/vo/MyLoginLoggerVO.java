package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Alias("lvo")
public class MyLoginLoggerVO {
	private int num;
	private String idn;
	private String reip,uagent;
	private String sstime,eetime;
	private String status;
}
