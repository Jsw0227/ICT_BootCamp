package kr.co.ictedu.myictstudy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.myictstudy.vo.MyLoginLoggerVO;

@Mapper
public interface MyLogDao {
	@Insert("insert into myloginlog values(myloginlogs.nextVal,"
			+ "#{idn},#{reip},#{uagent},#{status},sysdate)")
	public void addLoginLogging(MyLoginLoggerVO vo);
}
