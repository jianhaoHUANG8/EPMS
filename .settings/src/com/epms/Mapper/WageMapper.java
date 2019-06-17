package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.StaffWage;
import com.epms.Bean.Wage;
import com.epms.Bean.WelfareUser;
@Repository
public interface WageMapper {

	//�鿴���˹���
	public List<Wage> showWageByJobId(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//�鿴���˹�������
	public int countByJobId(int jobId);

	//��ӹ��ʼ�¼
	public void addWage(@Param("jobId")int jobId,@Param("wageTime") String wageTime,@Param("basicWage") double basicWage,
			@Param("overTimePay")double overTimePay,@Param("liveAllowance") double liveAllowance,@Param("holiday") double holidayAllowance,@Param("performAllowance") double performAllowance,@Param("absence") double absence,
			@Param("socialSecurity")double socialSecurity,@Param("hosingFund") double housingFund,@Param("borrow") double borrow,@Param("other") double other,
			@Param("wageTax")double wageTax,@Param("taxAmount") double taxAmount,@Param("sum") double sum);

	//��ѯ��������
	public StaffWage selectWageByJobId(int jobId);
	//��ѯ���ʼ�¼
	public Wage selectWage(int jobId,String wageTime);

	//�鿴��Ҫ������˵ļ�¼
	public List<Wage> showVerifyWage(@Param("before")int before,@Param("limit") int limit);

	//�鿴��Ҫ������˵ļ�¼����
	public int countVerifyWage();

}
