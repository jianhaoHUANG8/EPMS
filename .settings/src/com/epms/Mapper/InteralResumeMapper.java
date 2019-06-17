package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.InteralResume;

//�ڲ���Ա������
@Repository
public interface InteralResumeMapper
{
   int insertInteralResume(InteralResume interalResume);
    
    //ͨ�����Ų�ѯ�ύ�ļ�����Ϣ
    public List<InteralResume> selectInteralResumeByJobId(@Param("departmentId")String departmentId,
    		@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,@Param("after") int after,
    		@Param("jobId")int jobId);
	public int countByJobId(@Param("departmentId")String departmentId,@Param("occupationId")String occupationId,
			@Param("status")String status,@Param("jobId")int jobId);
	
	//����Ƿ��ظ��ύ
	int checkIfRepect(InteralResume interalResume);
	
	//���ž����ѯ�ڲ���Ա����
	public List<InteralResume> selectAllInteralResumeToManager(@Param("departmentId")String departmentId,
			@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,
			@Param("after") int after,@Param("jobId") int jobId,@Param("managerDepartmentId") int managerDepartmentId);
	public int countAllInteralResumeToManager(@Param("departmentId")String departmentId,@Param("occupationId")String occupationId,
			@Param("status")String status,@Param("jobId")int jobId,@Param("managerDepartmentId") int managerDepartmentId);

	//�ܾ����ѯȫ���ڲ���Ա����
	public List<InteralResume> selectAllInteralResume(@Param("departmentId")String departmentId,@Param("occupationId")String occupationId,
			@Param("status")String status,@Param("before") int before,@Param("after") int after);
	public int countAllInteralResume(@Param("departmentId")String departmentId,@Param("occupationId")String occupationId,
			@Param("status")String status);
	


}


