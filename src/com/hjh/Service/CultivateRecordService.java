package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.TotalData;

public interface CultivateRecordService 
{
	//��ѯĳ����ÿ��Ա������ѵ����
	public List<TotalData> CountCultivate(String year,String month);
}
