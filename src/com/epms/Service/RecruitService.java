package com.epms.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Recruit;

//��Ƹ�ƻ�����
public interface RecruitService {
	//�ύ��Ƹ�ƻ�
	public String insertRecruit(Recruit recruit);
	
	//�ϼ���ѯ�¼��ύ��ȫ����Ƹ��Ϣ
	public List<Recruit> selectAllRecruit(String occupationId,String departmentId,String status,int before,int after,int jobId);
	public int countAllRecruit(String occupationId,String departmentId,String status,int jobId);
	
	//�����Ƹ�ƻ�
	public String updateRecruitStatus(Recruit recruit);
	
	//��ѯ�Լ��ύ����Ƹ�ƻ�
	List<Recruit> selectAllRecruitByWriteId(String departmentId,String occupationId,String status,int before,int after,int writeId);
	int countSelectAllRecruitByWriteId(String departmentId,String occupationId,String status,int writeId);
	
	//Ա����ѯ��Ƹ�ƻ�
	List<Recruit> selectAllRecruitToEmployee(String occupationId,String departmentId,int before,int after);
	int countSelectAllRecruitToEmployee(String occupationId,String departmentId);
	
	

	
}
