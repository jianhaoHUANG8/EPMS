package com.epms.Service;

import java.util.List;

import com.epms.Bean.VacationRule;


public interface VacationRuleService 
{		
	//�޸ļ��ڹ涨����
	String updatetVacationRule(VacationRule vacationRule);
		
	//��ѯ���ڹ涨����
	List<VacationRule> selectVacationRule(int before,int after);
	int count();
	
	
}
