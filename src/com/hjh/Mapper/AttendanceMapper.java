package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.WorkingCalendar;

@Repository
public interface AttendanceMapper 
{
	 //�ϰ��
	 int insertInAttendance(Attendance attendance);
	 
	 //�°��
	 int insertOutAttendance(Attendance attendance);
	 
	 //����Ƿ����д�
	 Attendance SelectAttendanceByTodayAndJobId(Attendance attendance);	
	 
	 //ͨ��id��ѯȫ���򿨼�¼
	 List<Attendance> selectAllById (@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId,@Param("today")String today);
	 int count (@Param("jobId")int jobId,@Param("today")String today);
	 
	 /*//��������ģ����ѯÿ��Ա���ĳٵ������ˡ��Ӱ�ʱ��
	 List<Attendance> selectSumDataByDate(@Param("before") int before,@Param("after") int after,
			                              @Param("startDate")String startDate);
	 int countSumDataByDate (@Param("startDate")String startDate);*/
	 
	 //��ѯĳ��ÿ��Ա���ļӰ�(�԰�СʱΪ��λ)ʱ��
	 List<Attendance> selectSumOvertimeByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //��ѯĳ��ÿ��Ա���ĳٵ�(����)
	 List<Attendance> selectSumLatelyByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //��ѯĳ��ÿ��Ա��������(����)
	 List<Attendance> selectSumEarlyLeaveByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //��ѯĳ��ÿ��Ա���Ĺ����մ򿨴���
	 List<Attendance> countSumWorkDayByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 
	 
	 
	 
}
