package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.TotalData;


public interface AttendanceService 
{
	//�ϰ��
	String insertInAttendance(int jobId);
	
	//�°��
	String insertOutAttendance(int jobId);
	
	//����Ƿ����д�
	Attendance ChackDate(Attendance attendance);
	
	//ͨ��id��ѯȫ���򿨼�¼
	List<Attendance> selectAllById(int before,int after,int jobId,String today);	
    int count (int jobId,String today);
    
    /*//��������ģ����ѯÿ��Ա���ĳٵ������ˡ��Ӱ�ʱ��
    List<Attendance> selectSumDataByDate(int before,int after,String startDate);	
    int countSumDataByDate(String startDate);*/
    
    //��ѯĳ����ÿ��Ա���ĳٵ�(����)������(����)���Ӱ�(�԰�СʱΪ��λ)ʱ����
    List<TotalData> selectAllSumDataByInDate(String year,String month);
	
    //��ѯĳ����ÿ��Ա��ȱ�ڴ�����
    List<TotalData> selectAllSumAbsenceByInDate(String year,String month);
}
