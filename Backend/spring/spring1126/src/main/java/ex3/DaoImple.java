package ex3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
CREATE TABLE speedtest(
num number PRIMARY key,
uname VARCHAR2(400),
sdate date
);
*/
//Aop & JDBC에서 Statement , PrepareStatement 차이를 함께 학습
//<bean id="dao" class=/>
@Component("dao")
public class DaoImple implements DaoInter{

	@Autowired
	private MyConn myConn;
	public DaoImple() {
		System.out.println("DaoImple 생성자 호출!");
	}
	@Override
	public String firstStatementTest(int code) {
		
		//long start = System.currentTimeMillis();
		if(code == 1) {
			try (Connection con = myConn.getConnection();
					Statement stmt = con.createStatement()	
					){
				for(int i=1; i<= 10000; i++) {
					StringBuilder sql = new StringBuilder();
					sql.append("insert into speedtest values(");
					sql.append(i).append(",'");
					sql.append("xman" + i).append("',");
					sql.append("sysdate)");
					System.out.println("Log1 = > " + sql);
					stmt.executeUpdate(sql.toString());//DB에 명령어가 전달이 됨
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(code == 2) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into speedtest values(?,?,sysdate)");
			try ( 
				  Connection con = myConn.getConnection();
				  PreparedStatement pstmt = con.prepareStatement(sql.toString());
				){
				for(int i=1; i<=10000; i++) {
					pstmt.setInt(1, i);//num
					pstmt.setString(2, "xman"+i);
					pstmt.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			
		}
		//long end = System.currentTimeMillis();
		//System.out.println("소요시간"+(end - start));
		return "Check 완료*****";
	}
	@Override
	public String firstTest() {
		//long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.print(i + " ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//long end = System.currentTimeMillis();
		//System.out.println("소요시간"+(end - start));
		return "Check 완료*****";
	}
	// 반환 값을 받아서 처리할 After-returning Advice
	@Override
	public String second() {
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------").append("\n");
		sb.append("Atfer-returning Adivce:테스트").append("\n");
		sb.append("---------------------").append("\n");
		return sb.toString();
	}

	@Override
	public void third() {
		String[] ar = { "메세지1", "메세지2", "메세지3", "메세지4", "메세지5" };
		for (int i = 0; i <= ar.length; i++) {
			System.out.println("비니지니스 로직의 " + ar[i]);// 예외 발생!
		}
	}
	@Override
	public void myBefore() {
		System.out.println("핵심 비지니스 로직!!!!!!!!");
	}

}













