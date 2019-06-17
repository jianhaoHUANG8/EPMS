package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Recruit;
import com.epms.Bean.User;
//��Ƹ�ƻ�����
@Repository
public interface RecruitMapper {
	
	//�ύ��Ƹ�ƻ�
	int insertRecruit(Recruit recruit);
	int checkIfRepect(Recruit recruit);
	
	//�ϼ��鿴�¼��ύ����Ƹ�ƻ�
	List<Recruit> selectRecruitToTotalManager(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status,@Param("before") int before,@Param("after") int after);
	int countToTotalManager(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status);
	
	List<Recruit> selectRecruitToBoard(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status,@Param("before") int before,@Param("after") int after);
	int countToBoard(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("status") String status);
	
	//�¼��鿴�Լ��ύ���ϼ�����Ƹ�ƻ�
	List<Recruit> selectAllRecruitByWriteId(@Param("departmentId")String departmentId,
    		@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,@Param("after") int after,@Param("writeId") int writeId);
	int countSelectAllRecruitByWriteId(@Param("departmentId")String departmentId,
    		@Param("occupationId")String occupationId,@Param("status")String status,
    		@Param("writeId")int writeId);
	
	//������Ƹ�ƻ�
	public int updateRecruitStatus(Recruit recruit);
	
	//ͨ������id����ѯ������Ϣ
	public Recruit selectRecruitById(int id);
	
	//Ա����ѯ��Ƹ�ƻ�
	List<Recruit> selectAllRecruitToEmployee(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId,
			@Param("before") int before,@Param("after") int after);
	int countSelectAllRecruitToEmployee(@Param("occupationId") String occupationId,@Param("departmentId") String departmentId);
	



	



}
