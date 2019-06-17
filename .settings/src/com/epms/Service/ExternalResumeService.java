package com.epms.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.epms.Bean.ExternalResume;
//�ⲿ��Ա������
public interface ExternalResumeService 
{
	//�ύ����
	public String insertExternalResume(ExternalResume externalResume);
	
	//��ѯȫ���ⲿ��Ա����
	public List<ExternalResume> selectAllExternalResume(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countSelectAllExternalResume(String departmentId,String occupationId,String status,int jobId);	

	//�����ȡ
	public ExternalResume getExternalResumeByEmail(String email);



}
