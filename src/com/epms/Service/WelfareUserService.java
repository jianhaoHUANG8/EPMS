package com.epms.Service;

import java.util.List;

import com.epms.Bean.WelfareUser;

public interface WelfareUserService {

	//��ѯ���˸���
	List<WelfareUser> showWelfare(int jobId,int before, int after);

	//������˸�������
	int count(int jobId);

}
