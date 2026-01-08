package kr.co.ictedu.myictstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//Maven,Gradle 방식 -> 의존관계 라이브러리들을 자동으로 가져오고 관리
//maven 방식 :Xml 방식
//gradle 방식 : javascript 방식
@SpringBootApplication
public class MyictstudyApplication {

	public static void main(String[] args) {
		//SpringContainer가 시작및 종료
		SpringApplication.run(MyictstudyApplication.class, args);
	}
	//spring boot에서 비동기식 외부 접속[Cros Allow Origin]을 허용해주기 위한 설정
	//빈으로 등록 - 스프링 컨테이너가 관리할 객체 <bean ~
	@Bean
	public WebMvcConfigurer crosConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Cros Allow Origin 실행!");
				registry.addMapping("/**")
				//허용할 IP와 port를 지정 -> 리액트 아이피와 port
				.allowedOrigins("http://192.168.0.18:3001","http://192.168.0.18:3000",
						"http://localhost:3001","http://localhost:3000")
				.allowedHeaders("*")
				//  이거 추가해야 withCredentials가 동작함
				.allowCredentials(true)
				.allowedMethods("*").maxAge(3600);
			}
		};
	}
}







