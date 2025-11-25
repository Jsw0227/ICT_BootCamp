package ex2;

public class MySource {
	private String source;

	public MySource(String source) {
		this.source=source;
		System.out.println("생성자를 통해서 주입 받은 값 : "+ source);
	}
	
	public String reTurnSource() {
		StringBuilder sb = new StringBuilder();
		sb.append("DI 실습 : ");
		//Spring에 의해ㅓㅅ 생성자로 문자열을 저장한 값을 StringBuilder 객체에 저장해서 반환한다.
		sb.append(source);
		return sb.toString();
	}
	
}
