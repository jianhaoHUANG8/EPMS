package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.PerformSum;

@Repository
public interface PerformSumMapper {
	
	/*
	 * ��ѯ���˼�Ч���۵ļ�¼
	 */
	public List<PerformSum> showAllPerform(int jobId,int before,int after);
	//����������
	public int count(@Param("jobId")int jobId);
	//����ܼ�Ч�ɼ�
	public int addPerformSum(@Param("evaluateId")int evaluateId,@Param("score") double score,@Param("type") String type,@Param("assessDate") String assessDate);
	//��˼�Ч���ۼ�¼
	public int updateVerifyPerform(@Param("jobId")int jobId,@Param("nowTime")String nowTime,@Param("assessDate")String assessDate,@Param("approverId")int approverId);
	//��ʾ����Ĳ�������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ����Ĳ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countStaffVerifyPerform(int jobId);
	//��ȡ�ܾ�����Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ�ܾ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countManagerVerifyPerform(int jobId);
	//�ж�����˵����(�ܾ���/���ž���/����)
	public String findIdentityByJobId(int jobId);
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showDirectorVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countDirectorVerifyPerform(int jobId);
	//��ȡ���ž���/�ܾ���鿴Ա���ļ�Ч���ۼ�¼
	public List<PerformSum> showStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ���ž���/�ܾ���鿴Ա���ļ�Ч���ۼ�¼����
	public int countStaffPerform(int jobId);
	//��ȡ���²�ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showDirectorStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ���²�ѯԱ����Ч���ۼ�¼����
	public int countDirectorStaffPerform(int jobId);
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼����
	public int countGeneralManagerStaffPerform(int jobId);
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showManagerStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼����
	public int countManagerStaffPerform(int jobId);
	
	//ɾ���ܼ�Ч���۵ļ�¼
	public int deletePerformSum(@Param("jobId")int jobId,@Param("assessDate") String assessDate);

}
