package com.hjh.Service;

import java.util.List;
import com.hjh.Bean.Vacate;
import com.hjh.Bean.Vacation;


public interface VacationService 
{
	//��ѯʣ�����еļ������
	List<Vacation> selectVacationRemain(String type,int before,int after,int jobId);	
    int count (String type,int jobId);
	
	//����id�����Ͳ�ѯʣ������
    Vacation selectRemainByVacate(Vacate vacate);
    Vacation selectRemainByVacation(Vacation vacation);
  	
}
