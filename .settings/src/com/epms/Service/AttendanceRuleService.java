package com.epms.Service;

import com.epms.Bean.AttendanceRule;


public interface AttendanceRuleService 
{
	//�޸����°�ʱ��
	String updatetAttendanceRuleAll(AttendanceRule attendanceRule);
		
	//��ѯ���°�ʱ��
	AttendanceRule selectAttendanceRule();
	
}
