package com.hjh.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.SalaryAdjust;

@Repository
public interface SalaryAdjustMapper 
{
	//�ύн�ʵ�����
	public int insertSalaryAdjust(SalaryAdjust salaryAdjust);
	
	//���������id��ѯн�ʵ���������Ϣ
	List<SalaryAdjust> selectAllSalaryAdjustByWriteId(@Param("department_id") String department_id,@Param("state") String state,
			@Param("salaryadjust_type") String salaryadjust_type,@Param("before") int before,@Param("after") int after,@Param("jobId") int jobId);
	int countByWriteId(@Param("department_id") String department_id,@Param("state") String state,
			@Param("salaryadjust_type") String salaryadjust_type,@Param("jobId") int jobId);
	
	//ͨ������id��ѯ�����Ϣ
	SalaryAdjust selectSalaryAdjustByApplyId(int applyId);
	
	//����Ƿ����ظ�
	int selectIfRepeat(SalaryAdjust salaryAdjust);
	
	//�ϼ���ѯ�����ύ��н�ʵ�������
	List<SalaryAdjust> selectSalaryAdjustToLeader(@Param("department_id") String department_id,
			                                      @Param("state") String state,
			                                      @Param("salaryadjust_type") String salaryadjust_type,
			                                      @Param("before") int before,@Param("after") int after,
			                                      @Param("writeOccupationId") int writeOccupationId);
	int countToLeader(@Param("department_id") String department_id,@Param("state") String state,
			          @Param("salaryadjust_type") String salaryadjust_type,
			          @Param("writeOccupationId") int writeOccupationId);
	
	//���ݹ��Ų�ѯ��ص�н�ʵ�����¼
	List<SalaryAdjust> selectAllSalaryAdjustByJobId(@Param("state") String state,@Param("before") int before,
			           @Param("after") int after,@Param("jobId") int jobId);
	int countByJobId(@Param("state") String state,@Param("jobId") int jobId);
	
}
