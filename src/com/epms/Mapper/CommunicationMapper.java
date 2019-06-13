package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Communication;
import com.epms.Bean.Personalinfo;

@Repository
public interface CommunicationMapper 
{
	 //��ѯȫ��ͨѶ¼
	 List<Communication> selectAll(@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("name") String name,
			 @Param("before") int before,@Param("after") int after);
	 int count (@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("name") String name);
	 
	 //���ݹ��Ų�ѯͨѶ��Ϣ
	 Communication selectByJobId(@Param("jobId")int jobId);
	 
	 //�޸ĸ���ͨѶ��Ϣ
	 int updateCommunication(Communication communication);
}
