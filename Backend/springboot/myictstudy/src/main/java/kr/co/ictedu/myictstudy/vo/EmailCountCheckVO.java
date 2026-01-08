package kr.co.ictedu.myictstudy.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCountCheckVO {
    private boolean success;
    // "ok", "wrong", "exceeded"
    private String reason; 
}
