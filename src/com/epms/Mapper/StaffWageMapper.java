package com.epms.Mapper;

import org.springframework.stereotype.Repository;

import com.epms.Bean.StaffWage;

@Repository
public interface StaffWageMapper 
{
	//�޸Ĺ���
	int updatetStaffWage(StaffWage staffWage);
	
	//���ݹ��Ų�ѯ����
	StaffWage selectStaffWageByJobId(int jobId);
}
