package com.epms.Service;

import java.util.List;

import com.epms.Bean.PerformScore;

public interface PerformScoreService {

	//��ѯδ���۵ļ�¼
	public List<PerformScore> showAllEvaluate(int jobId,int before,int after);
	//����������
	public int count(int jobId);
	//��Ӽ�Ч����
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3, int as4, int as5, int as6, int as7, int as8, int as9, int as10, int as11, int as12, int as13, int as14, int as15, int as16, int as17, int as18, int as19, int as20);
	//��ѯ�Ƿ��������ۣ���������ܼ�Ч����
	public int checkEndPerform(int evaluateId,String assessDate,String type);
	//��Ӽ�Ч���۳�ʼ����
	public void addInitPerform();
	
}
