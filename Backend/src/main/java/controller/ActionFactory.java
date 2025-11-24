package controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Properties;

import model.Action;
import model.HelloAction;
import model.IndexAction;
//ActionFactory : 원래 Controller가 하던 역할을 서브시가 종료되는 것을 방지 하고, 책임을 분리하는 역할을 수행
// 객체를 생성하고 반환 즉 공장과 같은 역할 수행 - ObjectFactory 패턴이다.

public class ActionFactory {
	private static ActionFactory factory;
	//classinfo..properties에 설정한 key를 읽어내기 위한 객체를 생성한다.
	private Properties prop;
	//classinfo.properties 절대 경로를 초기화
	private String path;
	private ActionFactory() {
		prop = new Properties();
		path = "C:\\Users\\ict\\web\\workspace\\backend1124\\src\\main\\java\\controller\\classinfo.properties";
	}
	
	//synchronized : Thread 동기화 -> 다른 쓰레드의 간섭을 받지 않게 한다
	public synchronized static ActionFactory getInstance(){
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	
	public Action getAction(String cmd) {
		Action ref = null;
		
//		if(cmd.equals("index")) {
//			ref = new IndexAction();
//		}else if(cmd.equals("hello")) {
//			ref = new HelloAction();
//		}
		try (FileReader fr = new FileReader(path);){
			// Properties객체를 통해서 스트림으로 해당 키와 값을 읽어 오기 위한 메서드를 호출
			prop.load(fr);
			String cmdPath = prop.getProperty(cmd, "model.IndexAction");
			//클래스를 로딩
			Class<?> handler = Class.forName(cmdPath);
			//기본 생성자를 호출 해서 newInstance로 호출 jdk9 이상부터
			Constructor<?> ct = handler.getDeclaredConstructor();
			//생성자로 부터 객체를 생성하는 메서드인 newInstance() 호출해서 객체로 생성함.
			ref = (Action) ct.newInstance();
		} catch (Exception e) {
			
		}
		return ref;
	}
}
