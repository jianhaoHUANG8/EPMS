package com.hjh.Service;

import com.hjh.Bean.AttendanceRule;


public interface AttendanceRuleService 
{
	//�޸����°�ʱ��
	String updatetAttendanceRuleAll(AttendanceRule attendanceRule);
		
	//��ѯ���°�ʱ��
	AttendanceRule selectAttendanceRule();
	
}
