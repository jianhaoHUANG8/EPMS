package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;


public interface TotalDataService 
{		
	//���ݲ�ѯ����Ա�����µĳٵ������ˣ���٣��Ӱ࣬ȱ������
	List<TotalData> selectMonthTotalData(String year,String month);
	List<TotalData> selectTotalData(String year,String month,int before,int limit);
	int countTotalData();
}
