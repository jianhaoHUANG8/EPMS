package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Wage;
import com.hjh.Bean.WelfareUser;

public interface WageService {

	//�鿴���˹���
	List<Wage> showWageByJobId(int jobId, int before, int after);

	//�鿴���˹�������
	int countByJobId(int jobId);

}
