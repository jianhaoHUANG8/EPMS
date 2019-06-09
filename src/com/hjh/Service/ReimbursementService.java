package com.hjh.Service;

import java.util.Date;
import java.util.List;

import com.hjh.Bean.Reimbursement;

public interface ReimbursementService {

	//��ѯ���˱�����¼
	public List<Reimbursement> showReimbursementByJobId(int jobId, int before, int after);
	//��ѯ���˱�����¼����
	public int countByJobId(int jobId);
	//ͨ�����ͺ����Ͳ�ѯ������¼
	public Reimbursement findByJobIdTypeContent(int jobId,String type,String content);
	//��ӱ�����¼
	public int addReimburse(int jobId, String type, String content, String date, double amount);
	//��ѯ����˵ı�������
	public List<Reimbursement> showVerifyReimburse(int before, int after);
	//��ѯ����˵ı�����������
	public int countVerifyReimburse();
	//��˱���ͨ��
	public int verifyPerformYes(int jobId, String time, int verifyId,String verifyDate);
	//��˱�����ͨ��
	public int verifyPerformNo(int jobId, String time, int verifyId,String verifyDate);
	//��ѯ��˱�����¼
	public List<Reimbursement> showVerifyReimburseRecord(int jobId, int before,int after);
	//������˱�����¼����
	public int countVerifyReimburseRecord(int jobId);
	
}
