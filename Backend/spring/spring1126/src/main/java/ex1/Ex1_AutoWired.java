package ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//AutoWired : 자동 빈 묶기 , 의존성을 자동으로 연결  *****
//byName, byType구분하기 
public class Ex1_AutoWired {
	@Autowired
	@Qualifier("ictMsg1")
	private String msg;

	
	public String getMessage() {
		StringBuffer sb= new StringBuffer();
		sb.append("<p style='color:blue'>");
		sb.append(msg.charAt(0)).append(",");
		sb.append(msg);
		sb.append("</p>");
		return sb.toString();
	}
//	@Autowired
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
	
	
}
