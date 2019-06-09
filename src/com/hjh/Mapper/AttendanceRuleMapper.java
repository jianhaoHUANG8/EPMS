package com.hjh.Mapper;

import org.springframework.stereotype.Repository;

import com.hjh.Bean.AttendanceRule;

@Repository
public interface AttendanceRuleMapper 
{
	//�޸��ϰ�ʱ��
	int updatetAttendanceRuleStart(AttendanceRule attendanceRule);
	
	//�޸��°�ʱ��
	int updatetAttendanceRuleFinish(AttendanceRule attendanceRule);
	
	//�޸����°�ʱ��
	int updatetAttendanceRuleAll(AttendanceRule attendanceRule);
	
	//��ѯ���°�ʱ��
	AttendanceRule selectAttendanceRule();
	
}
