package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.WorkingCalendar;

@Repository
public interface WorkingCalendarMapper 
{
	//����������·ݲ�ѯ��������
	int CountWorkSum(@Param("year") String year,@Param("month") String month);
 
	//����������·ݲ�ѯ�ڼ�������
	int CountNotWorkSum(@Param("year") String year,@Param("month") String month);
	
	//�����������ж��Ƿ�Ϊ������
	int checkIfWorkingDay(@Param("today") String today);
	
	//��ѯĳ�µ����һ������
	String selectMonthLastDate(@Param("year") String year,@Param("month") String month);
	
	//��ѯĳ�µĵ�һ������
	String selectMonthFirstDate(@Param("year") String year,@Param("month") String month);
	
	//�������ڲ�ѯid
	int selectIdByDate(@Param("date") String date);
 
	//��ѯĳһ����Ľڼ�������
	int selectNotWorkBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
	
	//��ѯĳһ����Ĺ�������
    int selectWorkBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
    
    //����ĳһ�����������е�ÿһ�����ڶ�Ӧ��id
    List<WorkingCalendar> selectIdBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
    
    //��ѯĳһ�·ݵ�ȫ����������
    String[] selectWorkDate(@Param("year") String year,@Param("month") String month);  
    
    //��ѯĳһ�·ݵ�ȫ����Ϣ����
    String[] selectNotWorkDate(@Param("year") String year,@Param("month") String month); 
    
	
}
