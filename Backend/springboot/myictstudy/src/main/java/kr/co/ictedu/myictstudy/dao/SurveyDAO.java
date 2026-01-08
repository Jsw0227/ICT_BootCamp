package kr.co.ictedu.myictstudy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.ictedu.myictstudy.vo.SurveyContentVO;
import kr.co.ictedu.myictstudy.vo.SurveyResultVO;
import kr.co.ictedu.myictstudy.vo.SurveyVO;

@Mapper
public interface SurveyDAO {
	//<select id="maxSurveyNum" resultType="Long">
    Long maxSurveyNum();
    //<select id="findBySNUM" parameterType="Long" resultType="surveyresultvo">
    List<SurveyResultVO> findBySNUM(Long num);
    //<insert id="saveSurvey" parameterType="surveyvo">
    void saveSurvey(SurveyVO vo);
    //<insert id="saveSurveyContentList" parameterType="java.util.List">
    void saveSurveyContentList(List<SurveyContentVO> list);
    //<update id="incrementSurveyCount">
    void incrementSurveyCount(@Param("subcode") int subcode,
    		@Param("surveytype") String surveytype);

}
