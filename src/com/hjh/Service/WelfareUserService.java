package com.hjh.Service;

import java.util.List;
import com.hjh.Bean.WelfareUser;

public interface WelfareUserService {

	//��ѯ���˸���
	List<WelfareUser> showWelfare(int jobId,int before, int after);

	//������˸�������
	int count(int jobId);

}
