package com.epms.Service;

import java.util.List;

import com.epms.Bean.Resume;

//������
public interface ResumeService 
{
	//ͨ�����Ų�ѯ�ύ�ļ�����Ϣ
    public List<Object> selectAllResumeByJobId(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countByJobId(String departmentId,String occupationId,String status,int jobId);
	//��˼���
	public String updateAllResume(Resume resume,String interviewName);
	
	
}
