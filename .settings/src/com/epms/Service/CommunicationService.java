package com.epms.Service;

import java.util.List;

import com.epms.Bean.Communication;

public interface CommunicationService 
{
	//��ѯȫ��ͨѶ¼
    public List<Communication> selectAllCommunication (String departmentId,String jobId,String name,int before,int after);
    public int count (String departmentId,String jobId,String name);
  	
    //�޸ĸ���ͨѶ��Ϣ
    String updateCommunication(Communication communication);
    
    //���ݹ��Ų�ѯͨѶ��Ϣ
  	Communication selectByJobId(int jobId);
}
