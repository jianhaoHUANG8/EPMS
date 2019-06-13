package com.epms.Mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Reimbursement;
@Repository
public interface ReimbursementMapper {

	//��ѯ���˱�����¼
	public List<Reimbursement> showReimbursementByJobId(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//��ѯ���˱�����¼����
	public int countByJobId(int jobId);

	//ͨ�����š����͡����ݲ�ѯ������¼
	public Reimbursement findByJobIdTypeContent(@Param("jobId")int jobId,@Param("type") String type,@Param("content")String content);

	//��ӱ���
	public int addReimburse(@Param("jobId")int jobId,@Param("type") String type,@Param("content") String content,@Param("time")String time,@Param("amount") double amount);

	//��ѯ����˵ı�������
	public List<Reimbursement> showVerifyReimburse(@Param("before")int before,@Param("after") int after);

	//��ѯ����˵ı�����������
	public int countVerifyReimburse();

	//��˱���ͨ��
	public int verifyPerformYes(@Param("jobId")int jobId,@Param("time") String time,@Param("verifyId") int verifyId,@Param("verifyDate")String verifyDate);

	//��˱�����ͨ��
	public int verifyPerformNo(@Param("jobId")int jobId,@Param("time") String time,@Param("verifyId") int verifyId,@Param("verifyDate")String verifyDate);

	//��ѯ��˱�����¼
	public List<Reimbursement> showVerifyReimburseRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//������˱�����¼����
	public int countVerifyReimburseRecord(int jobId);

	

}
