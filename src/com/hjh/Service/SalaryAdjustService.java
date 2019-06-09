package com.hjh.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hjh.Bean.SalaryAdjust;


public interface SalaryAdjustService 
{
	//�ύн�ʵ�����
	public String insertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName);
	
	//���������id��ѯн�ʵ���������Ϣ
	List<SalaryAdjust> selectAllSalaryAdjustByWriteId(String department_id,String state,
			                        String salaryadjust_type,int before,int after,int jobId);
	int countByWriteId(String department_id,String state,String salaryadjust_type,int jobId);

	//�ϼ��鿴�����ύ��ȫ��н�ʵ�������
	public List<SalaryAdjust> selectAllSalaryAdjust(String department_id,String state,String salaryadjust_type,int before,int after,int jobId);	
	public int countAllSalaryAdjust (String department_id,String state,String salaryadjust_type,int jobId);
	
	//��������������Ϣ
  	String updateSalaryAdjust(SalaryAdjust salaryAdjust,String state);
  	
    //���ݹ��Ų�ѯ��ص�н�ʵ�����¼
  	List<SalaryAdjust> selectAllSalaryAdjustByJobId(String state,int before,int after,int jobId);
  	int countByJobId(String state,int jobId);

}



