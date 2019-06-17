package com.epms.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.ExternalResume;
//�ⲿ��Ա������
@Repository
public interface ExternalResumeMapper
{
  //�ύ���� 
  int insertExternalResume(ExternalResume externalResume);
  
  //��������id��ѯ����
  ExternalResume selectMyResume(int resumeId);
  
  //����Ƿ��ظ��ύ
  int checkIfRepect(ExternalResume externalResume);
  
  //��ѯȫ���ⲿ��Ա����
  public List<ExternalResume> selectAllExternalResume(@Param("departmentId")String departmentId,
			@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,@Param("after") int after);
  public int countSelectAllExternalResume(@Param("departmentId")String departmentId,
			@Param("occupationId")String occupationId,@Param("status")String status);
 
  //���ž����ѯ�ⲿ��Ա����
  public List<ExternalResume> selectAllExternalResumeToManager(@Param("departmentId")String departmentId,
			@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,@Param("after") int after,
		                                                       @Param("managerDepartmentId") int managerDepartmentId);
  public int countSelectAllExternalResumeToManager(@Param("departmentId")String departmentId,
			@Param("occupationId")String occupationId,@Param("status")String status,@Param("managerDepartmentId") int managerDepartmentId);
  
    //ͨ�����ֵ绰��ȡ����
	public ExternalResume getExternalResumeByEmail(@Param("email")String email);
}
