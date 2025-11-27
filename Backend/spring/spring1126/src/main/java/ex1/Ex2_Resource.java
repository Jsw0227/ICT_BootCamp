package ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Ex2_Resource {
	@Autowired
	@Qualifier("q2")
	private Ex2_MyResource res;
	public Ex2_MyResource getRes() {
		return res;
	}
//	public void setRes(Ex2_MyResource res) {
//		this.res = res;
//	}
	
}
