package com.epms.Service;

import java.util.List;

import com.epms.Bean.InteralResume;
//�ڲ���Ա������
public interface InteralResumeService 
{
	//�ڲ���Ա��д����
	public String insertInteralResume(InteralResume interalResume);
	
	//ͨ�����Ų�ѯ�ύ�ļ�����Ϣ
    public List<InteralResume> selectInteralResumeByJobId(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countByJobId(String departmentId,String occupationId,String status,int jobId);
	
	//�ܾ�����ž����ѯ������Ϣ
	public List<InteralResume> selectAllInteralResume(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countAllInteralResume(String departmentId,String occupationId,String status,int jobId);
}
