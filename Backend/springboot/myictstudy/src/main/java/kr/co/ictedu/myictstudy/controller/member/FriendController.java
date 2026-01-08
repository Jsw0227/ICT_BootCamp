package kr.co.ictedu.myictstudy.controller.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.co.ictedu.myictstudy.service.FriendService;
import kr.co.ictedu.myictstudy.vo.FriendRequestVO;
import kr.co.ictedu.myictstudy.vo.MemberVO;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;
    //GET => /api/friends/members : Login이후에 호출됨 , 쿼리에 따라서 본인은 제외하고 회원 목록 
    @GetMapping("/members")
    public List<MemberVO> getAllMembers(HttpSession session) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        return friendService.getAllExcept(loginMember.getUserid());
    }
    // 친구 요청 전송
    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestBody Map<String, String> body, HttpSession session) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        String receiverId = body.get("receiverId");
        friendService.sendRequest(loginMember.getUserid(), receiverId);
        return ResponseEntity.ok("친구 요청 완료");
    }
    // 받은 친구 요청
    @GetMapping("/incoming")
    public List<FriendRequestVO> getIncomingRequests(HttpSession session) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        return friendService.getPendingRequests(loginMember.getUserid());
    }
    // 친구 요청 수락/거절
    @PostMapping("/respond")
    public ResponseEntity<?> respondToRequest(@RequestBody Map<String, String> body) {
        Long requestId = Long.parseLong(body.get("id"));
        String action = body.get("action"); // "accept" or "reject"
        friendService.respond(requestId, action);
        return ResponseEntity.ok("처리 완료");
    }
   // 나의 친구 목록
    @GetMapping("/myfriends")
    public List<MemberVO> myFriends(HttpSession session) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        return friendService.getFriends(loginMember.getUserid());
    }
    // 보낸 친구 요청 리스트 반환
    @GetMapping("/outgoing")
    public List<FriendRequestVO> getOutgoingRequests(HttpSession session) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        return friendService.getSentRequests(loginMember.getUserid());
    }
}
