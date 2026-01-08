package kr.co.ictedu.myictstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.myictstudy.dao.FriendRquestDao;
import kr.co.ictedu.myictstudy.vo.FriendRequestVO;
import kr.co.ictedu.myictstudy.vo.MemberVO;

@Service
public class FriendService {
	    @Autowired
	    private FriendRquestDao dao;
	    public List<MemberVO> getAllExcept(String member_num) {
	        return dao.selectAllExceptMe(member_num);
	    }
	    public void sendRequest(String from, String to) {
	        dao.sendRequest(from, to);
	    }
	    public List<FriendRequestVO> getPendingRequests(String member_num) {
	        return dao.getPending(member_num);
	    }
	    public void respond(Long id, String action) {
	        dao.updateStatus(id, action.equals("accept") ? "accepted" : "rejected");
	    }
	    public List<MemberVO> getFriends(String member_num) {
	        return dao.getFriends(member_num);
	    }
	    public List<FriendRequestVO> getSentRequests(String member_num) {
	        return dao.getSentRequests(member_num);
	    }
}
