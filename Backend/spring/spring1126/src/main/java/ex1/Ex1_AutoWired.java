package ex1;
//AutoWired : 자동 빈 묶기 , 의존성을 자동으로 연결 *****
//byName, byType 구분하기
//byName : Properties 와 id가 같을 경우 , 현재 페이지의 private String msg 와 ex1_auto 페이지의 id값
//byType : 자료형이 같은 경우
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Ex1_AutoWired {
	@Autowired 
	@Qualifier("ictMsg2")
	private String msg;
	
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
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
