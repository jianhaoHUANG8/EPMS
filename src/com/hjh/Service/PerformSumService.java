package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.PerformSum;

public interface PerformSumService {
	
	//��ѯ���˵ļ�Ч���ۼ�¼
	public List<PerformSum> showAllPerform(int jobId,int before,int after);
	//�����¼��������
	public int count(int jobId);
	//��ʾ����Ĳ�������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showVerifyPerform(int jobId, int before, int after);
	//��ȡ����Ĳ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countStaffVerifyPerform(int jobId);
	//��˼�Ч���ۼ�¼
	public int updateVerifyPerform(int jobId, String assessDate, int approverId);
	//��ȡ�ܾ�����Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerVerifyPerform(int jobId,int before, int after);
	//��ȡ�ܾ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countManagerVerifyPerform(int jobId);
	//ͨ�����Ż�ȡ��ݣ��ܾ������ž������£�
	public String findIdentityByJobId(int jobId);
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showDirectorVerifyPerform(int jobId,int before, int after);
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countDirectorVerifyPerform(int jobId);
	//��Ч���۲�ͨ��
	public int updateVerifyPerformNo(int jobId, String assessDate);
	//��ȡ���²�ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showDirectorStaffPerform(int jobId, int before,int after);
	//��ȡ���²�ѯԱ����Ч���ۼ�¼����
	public int countDirectorStaffPerform(int jobId);
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerStaffPerform(int jobId,int before, int after);
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼����
	public int countGeneralManagerStaffPerform(int jobId);
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showManagerStaffPerform(int jobId, int before,int after);
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼����
	public int countManagerStaffPerform(int jobId);
	
	

}
