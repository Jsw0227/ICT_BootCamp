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
