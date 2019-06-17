package com.epms.Service;

import java.util.List;

import com.epms.Bean.CultivateRecord;
import com.epms.Bean.TotalData;


public interface CultivateRecordService 
{
	//������ѵ
	String insertCultivateRecord(int cultivateId,int participatorId);
	
	//Ա����ѯ�Լ���������ѵ�γ�
	List<CultivateRecord> selectCultivateRecordByJobId(String cultivateId,String status,int before,int after,int jobId);
	int countSelectCultivateRecordByJobId(String cultivateId,String status,int jobId);
	
	//�ϼ���ѯ������������ȫ����ѵ��¼
	List<CultivateRecord> selectAllCultivateRecord(int before,int after,int leaderId);
	int countselectAllCultivateRecord(int leaderId);
	
	//�����ѵ����
	String updateCultivateRecordStatus(int recordId,String recordStatus,String status,int cultivateId);

	//��ѯĳ����ÿ��Ա������ѵ����
	public List<TotalData> CountCultivate(String year,String month);
	
}
