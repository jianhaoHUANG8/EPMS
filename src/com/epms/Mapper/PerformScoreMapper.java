package com.epms.Mapper;

import java.util.List;















import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.PerformScore;
import com.epms.Bean.User;

@Repository
public interface PerformScoreMapper {
	
	//��ѯδ���۵ļ�¼
	public List<PerformScore> showAllEvaluate(@Param("jobId")int jobId,@Param("before")int before,@Param("after")int after);
	public int count(@Param("jobId")int jobId);
	
	//��Ӽ�Ч����
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3,
			int as4, int as5, int as6, int as7, int as8, int as9, int as10,
			int as11, int as12, int as13, int as14, int as15, int as16,
			int as17, int as18, int as19, int as20);
	
	//��ѯ���������Ƿ�Ϊ�ܾ���
	public String checkGeneralManager(@Param("evaluateId")int evaluateId);
	//��ѯ��������
	public int checkManagerNum();
	//��ѯ��������
	public int checkPerformNum(@Param("evaluateId")int evaluateId,@Param("assessDate") String assessDate);
	//���㼨Ч�ܷ�
	public int countPerformScore(@Param("evaluateId")int evaluateId,@Param("assessDate") String assessDate);
	//��ѯ���������Ƿ�Ϊ���ž���
	public int checkManager(@Param("evaluateId")int evaluateId);
	//�����ڵ�����
	public int checkPeopleNum(@Param("jobId")int evaluateId);
	//��ѯ���������Ƿ�Ϊ��ְͨԱ
	public int checkStaff(@Param("evaluateId")int evaluateId);
	//�޸�����״̬
	public int updatePerformScoreState(@Param("jobId")int jobId,@Param("assessDate")String assessDate);
	//��ʼ���ܾ���Ч����
	public int initGeneralManagerEvaluate(@Param("jobId")int jobId,@Param("managerList")List<User> managerList,@Param("nowTime")String nowTime);
	//��ʼ�����ž�����ܾ���ļ�Ч����
	public int addMToGMEvaluate(@Param("managerList")List<User> managerList,@Param("GMjobId") int GMjobId,@Param("nowTime")String nowTime);
	//��ʼ�����ž���Բ�����Ա���ļ�Ч����
	public int addMToStaffEvaluate(@Param("MjobId")String MjobId,@Param("staffList") List<User> staffList,@Param("nowTime")String nowTime);
	//��ʼ��������Ա���Բ��ž���ļ�Ч����
	public int addStaffToMEvaluate(@Param("staffList")List<User> staffList,@Param("MjobId") String MjobId,@Param("nowTime")String nowTime);
	//��ʼ��������Ա����Ա���ļ�Ч����
	public int addStaffToStaff(@Param("SjobId")String SjobId,@Param("colleagueList") List<User> colleagueList,@Param("nowTime")String nowTime);
}
