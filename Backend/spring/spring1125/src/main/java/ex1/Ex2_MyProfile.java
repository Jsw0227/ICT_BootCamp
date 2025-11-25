package ex1;
// ex2.xml에서 스프링 컨테이너가 관리할 객체

public class Ex2_MyProfile {
	private String name;
	private int age;
	private String addr;
	//비지니스 메서드 즉, getBean()해서 불러와서 사용 할 메서드
	public String printProfile() {
		return name+":"+age+":"+addr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
