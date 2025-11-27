package ex2;
//DI대상이 되는 객체
public class Ex1_MyResource {
	private String conts;
	public void setConts(String conts) { 
		this.conts = conts;
	}
	public String resource1() {
		return conts;
	}
}
