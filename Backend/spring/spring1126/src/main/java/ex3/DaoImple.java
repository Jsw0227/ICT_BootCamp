package ex3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * CREATE TABLE speedtest(
num NUMBER PRIMARY key,
uname VARCHAR2(400),
sdate DATE
);	
 * 
 */
//Aop & JDBC에서 Statement , PrepareStatement 차이를 함께 학습
//<Bean id="dao" class=/>
@Component("dao")
public class DaoImple implements DaoInter {

	@Autowired
	private myConn myConn;

	public DaoImple() {
		System.out.println("DaoImple 생성자 호출!");
	}

	@Override
	public String firstStatementTest(int code) {
		//long start = System.currentTimeMillis();
		if (code == 1) {
			try (Connection con = myConn.getConnection(); Statement stmt = con.createStatement()) {
				for (int i = 1; i <= 10000; i++) {
					StringBuilder sql = new StringBuilder();
					sql.append("insert into speedtest values(");
					sql.append(i).append(",'");
					sql.append("xman" + i).append("',");
					sql.append("sysdate)");
					System.out.println("Log1 =>" + sql);
					stmt.executeUpdate(sql.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (code == 2) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into speedtest values(?,?,sysdate)");

			try (Connection con = myConn.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql.toString())) {
				for (int i = 1; i <= 10000; i++) {
					pstmt.setInt(1, i); // num
					pstmt.setString(2, "xman" + i);
					pstmt.executeUpdate();
				}
			} catch (SQLException e) { // SQL에서 오류가 넘어오기 때문에 SQLException으로 받아오는 것을 습관화 들여야한다.
				e.printStackTrace();
			}

		} else {

		}
		//long end = System.currentTimeMillis();
		//System.out.println("소요시간" + (end - start));
		return null;
	}

	@Override
	public String firstTest() {
		//long start = System.currentTimeMillis();
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println(i + " ");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//long end = System.currentTimeMillis();
		//System.out.println("소요시간" + (end - start));
		return "Check 완료 ****";
	}

}
