package ex2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;

public class Ex1_Resource {
	@Resource(name="resn1") // byName이 더 강하다
//	@Qualifier("q2") // byType이 더 강하다
	private Ex1_MyResource res;
	public Ex1_MyResource getRes() {
		return res;
	}
}
