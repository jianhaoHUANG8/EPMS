package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;
import com.epms.Bean.Wage;

public interface WageService {

	//�鿴���˹���
	List<Wage> showWageByJobId(int jobId, int before, int after);

	//�鿴���˹�������
	int countByJobId(int jobId);

	//���㹤��
	void countStaffWage(List<TotalData> totalData);

	//�鿴��Ҫ������˵ļ�¼
	List<Wage> showVerifyWage(int before, int limit);

	//�鿴��Ҫ������˵ļ�¼����
	int countVerifyWage();

}
