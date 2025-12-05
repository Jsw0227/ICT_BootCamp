package kr.co.ictedu.myictstudy.vo;
import org.apache.ibatis.type.Alias;
import lombok.Getter;
import lombok.Setter;
@Alias("upbcomm")
@Getter
@Setter
public class UpBoardCommVO {
    private int num;           
    private int ucode;       
    private String uwriter;   
    private String ucontent;   
    private String reip;       
    private String uregdate;  
}
/*
 create table upboard_comm (
    num number primary key,               
    ucode number,                          -- 어떤 게시글에 대한 댓글인지 (FK)
    uwriter varchar2(34) not null,         
    ucontent varchar2(400) not null,       
    reip varchar2(23),                     
    uregdate date default sysdate,         
    constraint fk_upboard_num
        foreign key (ucode)
        references upboard(num)
);
create sequence upboard_comm_seq
increment by 1
start with 1; 
 * */
 