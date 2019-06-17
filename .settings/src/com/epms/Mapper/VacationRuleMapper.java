package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.VacationRule;

@Repository
public interface VacationRuleMapper 
{
	//�޸ļ��ڹ涨����
	int updatetVacationRule(VacationRule vacationRule);
	
	//��ѯ���ڹ涨����
    List<VacationRule> selectVacationRule(@Param("before") int before,@Param("after") int after);
    int count();
	
}
