package kr.co.ictedu.myictstudy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.ictedu.myictstudy.vo.FriendRequestVO;
import kr.co.ictedu.myictstudy.vo.MemberVO;

@Mapper
public interface FriendRquestDao {
	//MyBatis에서 SQL 매퍼로 파라미터를 전달할 때 이름을 지정 할 수 있다아!!
	//이러면 xml에서는 parameterType을 생략 하게 된다.
	//본인을 제외하고 전체 회원들의 값을 VO에 담아서 List로 반환 
	List<MemberVO> selectAllExceptMe(@Param("member_num") String userid);
	// 친구 신청
    void sendRequest(@Param("requester_id") String requesterId, 
    		@Param("receiver_id") String receiverId);

    // 나에게 온 요청 목록을 VO에 담아서 List로 반환 
    List<FriendRequestVO> getPending(@Param("member_num") String userid);
    // 친구 요청 수락/거절
    void updateStatus(@Param("id") Long id, @Param("status") String status);
    // 나의 친구 목록을 VO에 담아서 List로 반환 
    List<MemberVO> getFriends(@Param("member_num") String userid);
    // 내가 보낸 친구 요청 목록 조회
    List<FriendRequestVO> getSentRequests(@Param("member_num") String userid);
    //친구 요청 존재 여부 확인 - 재요청을 할 때 구분용
    int checkRequestExists(@Param("requester_id") String requesterId, 
    		@Param("receiver_id") String receiverId);
    // 만약 기존 요청이 있는 경우 상태를 다시 pending으로 갱신 
    void resendRequest(@Param("requester_id") String requesterId, 
    		@Param("receiver_id") String receiverId);
}
    
