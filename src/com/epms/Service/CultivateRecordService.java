package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;

public interface CultivateRecordService 
{
	//��ѯĳ����ÿ��Ա������ѵ����
	public List<TotalData> CountCultivate(String year,String month);
}
