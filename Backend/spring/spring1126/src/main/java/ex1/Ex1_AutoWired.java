package ex1;
//AutoWired : 자동 빈 묶기 , 의존성을 자동으로 연결 *****
//byName, byType 구분하기
public class Ex1_AutoWired {
	private String msg;
	
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("<p style='color:blue'>");
		sb.append(msg.charAt(0)).append(",");
		sb.append(msg);
		sb.append("</p>");
		return sb.toString();
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
