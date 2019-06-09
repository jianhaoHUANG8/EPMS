package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Apply;


public interface ApplyService 
{
	//���ž����ύ���������
	public String insertApply(Apply apply,String passiveName);

	//�鿴ȫ������������Ϣ
	public List<Apply> selectAllApply(String department_id,String state,String applytype_name,
			                          int before,int after,int jobId);	
    public int count (String department_id,String state,String applytype_name,int jobId);
    
    //���������id��ѯ����������Ϣ
  	List<Apply> selectAllApplyByWriteId(String department_id,String state,String applytype_name,
  			                            int before,int after,int jobId);
  	int countByWriteId(String department_id,String state,String applytype_name,int jobId);
    
    //��������������Ϣ
  	String updateApply(Apply apply);
}
