package kr.co.ictedu.myictstudy.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.ictedu.myictstudy.dao.MemoDao;
import kr.co.ictedu.myictstudy.vo.MemoVO;
//Model[@RestController,@Controler] = Service = Dao[I][@Mapper]
@Service
public class MemoService {
	@Autowired
	private MemoDao memoDao;
	//Controller에서 입력받은 값을 VO로 받아서
	//MyBatis의 mapper에 <insert id="add" parameterType="memovo">
	//해당 id를 호출하면서 vo값을 전달해서 데이터베이스에 쿼리를 전송한다.
	public  void add(MemoVO vo) {
		//ss.insert("ns.id",value);
		memoDao.add(vo);
	}
	public List<MemoVO> list(Map<String, String> map){
		return memoDao.list(map);
	}
	public  MemoVO detail(int num) {
		return memoDao.detail(num);
	}
	public  void del(int num) {
		memoDao.del(num);
	}
	public int totalCount() {
		return memoDao.totalCount();
	}
}





