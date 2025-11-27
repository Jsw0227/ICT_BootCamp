package ex2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;

public class Ex1_Resource {
	@Resource(name="resn2")//@Resource(name = "resn2") 만으로도 충분하다.
	//@Qualifier("q1")
	private Ex1_MyResource res;
	public Ex1_MyResource getRes() {
		return res;
	}
}
