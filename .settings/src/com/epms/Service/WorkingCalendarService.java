package com.epms.Service;

import org.apache.ibatis.annotations.Param;


public interface WorkingCalendarService 
{		
	// ����������·ݲ�ѯ��������
	int CountWorkSum(String year,String month);

	// ����������·ݲ�ѯ�ڼ�������
	int CountNotWorkSum(String year,String month);
	
	
}
