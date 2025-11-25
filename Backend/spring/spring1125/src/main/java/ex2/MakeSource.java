package ex2;

public class MakeSource {
	private MySource source;
	
	public void setSource(MySource source) {
		this.source = source;
		System.out.println("MakeSource Setter 호출 시 Source 주소값 =>" + source);
		
	}
	
	public String result() {
		System.out.println(source.reTurnSource());
		return source.reTurnSource();
	}
	
}
