package com.epms.Service;

import java.util.List;

import com.epms.Bean.CultivateApply;


public interface CultivateApplyService {
	//�ύ��ѵ����
	public String insertCultivateApply(CultivateApply cultivateApply,int jobId);

	//��ѯ�Լ��ύ����ѵ�ƻ�
	List<CultivateApply> selectAllCultivateApplyByWriteId(String cultivateId,String status,
			int before,int after, int writeId);
	int countSelectAllCultivateApplyByWriteId(String cultivateId,String status,int writeId);
	
	//�ϼ���ѯ�¼��ύ��ȫ����ѵ��Ϣ
	public List<CultivateApply> selectAllCultivateApply(int before,int after,int jobId);
	public int countAllCultivateApply(int jobId);
	//�����ѵ�ƻ�
	public String updateCultivateApplyStatus(CultivateApply cultviateApply);	
	//Ա����ѯ��ѵ�ƻ�
	List<CultivateApply> selectAllCultivateApplyToEmployee(int before,int after);
	int countSelectAllCultivateApplyToEmployee();
	
	
	
}
