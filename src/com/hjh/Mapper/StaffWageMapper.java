package com.hjh.Mapper;

import org.springframework.stereotype.Repository;
import com.hjh.Bean.StaffWage;

@Repository
public interface StaffWageMapper 
{
	//�޸Ĺ���
	int updatetStaffWage(StaffWage staffWage);
	
	//���ݹ��Ų�ѯ����
	StaffWage selectStaffWageByJobId(int jobId);
}
