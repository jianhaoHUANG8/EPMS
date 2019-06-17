package com.epms.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Reimbursement;
import com.epms.Mapper.ReimbursementMapper;
import com.epms.Service.ReimbursementService;
@Service("reimbursementService")
public class ReimbursementServiceImpl implements ReimbursementService{

	@Autowired
	private ReimbursementMapper reimbursementMapper;
	
	//��ѯ���˱�����¼
	public List<Reimbursement> showReimbursementByJobId(int jobId, int before,int after) {
		return reimbursementMapper.showReimbursementByJobId(jobId,before,after);
	}
	
	//��ѯ���˱�����¼����
	public int countByJobId(int jobId) {
		return reimbursementMapper.countByJobId(jobId);
	}

	//ͨ�����ͺ����Ͳ�ѯ������¼
	public Reimbursement findByJobIdTypeContent(int jobId,String type, String content) {
		return reimbursementMapper.findByJobIdTypeContent(jobId,type,content);
	}

	//��ӱ�����¼
	public int addReimburse(int jobId,String type, String content,String time, double amount) {
		return reimbursementMapper.addReimburse(jobId,type,content,time,amount);
	}

	//��ѯ����˵ı�������
	public List<Reimbursement> showVerifyReimburse(int before, int after) {
		return reimbursementMapper.showVerifyReimburse(before,after);
	}

	//��ѯ�������ı�����������
	public int countVerifyReimburse() {
		return reimbursementMapper.countVerifyReimburse();
	}

	//��˱���ͨ��
	public int verifyPerformYes(int jobId, String time, int verifyId,String verifyDate) {
		return reimbursementMapper.verifyPerformYes(jobId,time,verifyId,verifyDate);
	}

	//��˱�����ͨ��
	public int verifyPerformNo(int jobId, String time, int verifyId,String verifyDate) {
		return reimbursementMapper.verifyPerformNo(jobId,time,verifyId,verifyDate);
	}

	//��ѯ��˱�����¼
	public List<Reimbursement> showVerifyReimburseRecord(int jobId, int before,int after) {
		return reimbursementMapper.showVerifyReimburseRecord(jobId,before,after);
	}

	//������˱�����¼����
	public int countVerifyReimburseRecord(int jobId) {
		return reimbursementMapper.countVerifyReimburseRecord(jobId);
	}

}
