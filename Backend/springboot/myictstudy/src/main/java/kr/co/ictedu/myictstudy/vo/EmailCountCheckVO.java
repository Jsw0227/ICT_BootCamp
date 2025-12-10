package kr.co.ictedu.myictstudy.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailCountCheckVO {
	private boolean success;
	private String reason;
}
