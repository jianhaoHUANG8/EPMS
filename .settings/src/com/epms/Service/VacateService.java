package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;
import com.epms.Bean.Vacate;


public interface VacateService 
{
	//���������Ϣ
	String insertVacate(Vacate vacate);
	
	//ͨ��id��ѯȫ�������Ϣ
	List<Vacate> selectAllVacateApplyByJobId(String state,String type,int before,int after,int jobId);	
	int count (String state,String type,int jobId);
	
	//��ѯȫ��ֱ���¼��ύ�������������(�ϼ���ѯ������)
	List<Vacate> selectAllVacateApply (String department_id,String state,String type,int before,int after,int jobId);
	int countToLeader (String department_id,String state,String type,int jobId);
	
	//�������������Ϣ
  	String updateVacate(Vacate vacate);
  	
    //���ݹ��Ų�ѯ��ͨ����ȫ����ټ�¼
  	public List<Vacate> selectPassVacateByJobId (int before,int after,int jobId);
  	int countPassVacateByJobId (int jobId);
  	
	//�������������Ϣ
  	String cancelVacateApply(Vacate vacate);
  	
    //��ѯȫ����Ա���µ��������
  	List<TotalData> selectAllVacateSum(String year,String month);
  	
  	
}
