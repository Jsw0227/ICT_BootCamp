package kr.co.ictedu.myictstudy.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCheckVO {
    private String email;
    private String code; //임시 토큰
    //@AllArgsConstructor
//	public EmailCheckVO(String email, String code) {
//		super();
//		this.email = email;
//		this.code = code;
//	}
    //@NoArgsConstructor
//    public EmailCheckVO() {
//		// TODO Auto-generated constructor stub
//	}
    
}




