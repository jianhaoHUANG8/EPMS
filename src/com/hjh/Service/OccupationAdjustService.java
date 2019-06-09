package com.hjh.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hjh.Bean.OccupationAdjust;
public interface OccupationAdjustService 
{
	//�ύн�ʵ�����
	public String insertOccupationAdjust(OccupationAdjust occupationAdjust,String passiveName);
	
	//���������id��ѯн�ʵ���������Ϣ
	List<OccupationAdjust> selectAllOccupationAdjustByWriteId(String pre_departmentid,String state,
			               String type,int before,int after,int jobId);
	int countByWriteId(String pre_departmentid,String state,String type,int jobId);
	
	//�ϼ��鿴�����ύ��ȫ��ְλ��������
	public List<OccupationAdjust> selectAllSalaryAdjust(String pre_departmentid,String state,String type,int before,int after,int jobId);	
	public int countAllOccupationAdjust (String pre_departmentid,String state,String type,int jobId);
	
	//��������������Ϣ
  	String updateOccupationAdjust(OccupationAdjust occupationAdjust,String state);
  	
	//���ݹ��Ų�ѯ��ص�ְλ������¼
	List<OccupationAdjust> selectAllOccupationAdjustByJobId(String state,String type,int before, int after,int jobId);
	int countByJobId(String state,String type,int jobId);

}



