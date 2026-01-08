package kr.co.ictedu.myictstudy.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("frvo")
public class FriendRequestVO {
    private int id;
    private String requester_id;
    private String receiver_id;
    private String status;
    private String request_date;
}
