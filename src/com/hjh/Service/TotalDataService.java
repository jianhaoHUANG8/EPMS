package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.TotalData;


public interface TotalDataService 
{		
	//���ݲ�ѯ����Ա�����µĳٵ������ˣ���٣��Ӱ࣬ȱ������
	List<TotalData> selectMonthTotalData(String year,String month);
}
