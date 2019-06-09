package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjh.Bean.PerformScore;
import com.hjh.Bean.User;
import com.hjh.Mapper.PerformScoreMapper;
import com.hjh.Mapper.PerformSumMapper;
import com.hjh.Mapper.UserMapper;
import com.hjh.Service.PerformScoreService;
@Service("performScoreService")
public class PerformScoreServiceImpl implements PerformScoreService{
	@Autowired
	private PerformScoreMapper performScoreMapper;
	@Autowired
	private PerformSumMapper performSumMapper;
	@Autowired
	private UserMapper userMapper;
	
	/*
	 * ��ѯδ���۵ļ�¼
	 */
	public List<PerformScore> showAllEvaluate(int jobId,int before, int after) {
		return performScoreMapper.showAllEvaluate(jobId,before,after);
	}

	/*
	 * ����������
	 */
	public int count(int jobId) {
		return performScoreMapper.count(jobId);
	}

	/*
	 * ��Ӽ�Ч����
 	 */
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3,
			int as4, int as5, int as6, int as7, int as8, int as9, int as10,
			int as11, int as12, int as13, int as14, int as15, int as16,
			int as17, int as18, int as19, int as20) {

		return performScoreMapper.addPerform(jobId,evaluateId,score,date,as1,as2,as3,
			as4,as5,as6,as7,as8,as9,as10,
			as11,as12,as13,as14,as15,as16,
			as17,as18,as19,as20);
	}

	
	//��ѯ�Ƿ��������ۣ���������ܼ�Ч����
	public int checkEndPerform(int evaluateId, String assessDate,String type) {
		long s=System.currentTimeMillis();//��ȡ��ʼʱ��
		if(performScoreMapper.checkStaff(evaluateId)==0){//��ѯ���������Ƿ�Ϊ��ְͨԱ
			long s2=System.currentTimeMillis();//��ȡ��ʼʱ��
			//��ְͨԱ��������
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			//Ӧ������=ͬ����+���ž���
			int peopleNum=performScoreMapper.checkPeopleNum(evaluateId)-1;
			System.out.println("performNum:"+performNum+" peopleNum"+peopleNum);
			if(performNum==peopleNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/peopleNum;
				//����ܼ�Ч�ɼ�
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("����ܳɼ�");
			}
			long e2=System.currentTimeMillis();//��ȡ����ʱ��
			System.out.println("��ְͨԱ������ʱ�䣺"+(e2-s2)+"ms");//�����������ʱ��

		}else if(performScoreMapper.checkManager(evaluateId)==1){//��ѯ���������Ƿ�Ϊ���ž���
			long s1=System.currentTimeMillis();//��ȡ��ʼʱ��
			//���ž���ı�����
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			//Ӧ������=����������+�ܾ���
			int peopleNum=performScoreMapper.checkPeopleNum(evaluateId);
			if(performNum==peopleNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/peopleNum;
				//����ܼ�Ч�ɼ�
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("����ܳɼ�");
			}
			System.out.println("performNum:"+performNum+" peopleNum:"+peopleNum);
			long e1=System.currentTimeMillis();//��ȡ����ʱ��
			System.out.println("���ž�������ʱ�䣺"+(e1-s1)+"ms");//�����������ʱ��
		}else if(performScoreMapper.checkGeneralManager(evaluateId).equals("�ܾ���")){//��ѯ���������Ƿ�Ϊ�ܾ���
			long s3=System.currentTimeMillis();//��ȡ��ʼʱ��
			//����ı�����
			int managerNum=performScoreMapper.checkManagerNum()-1;
			//Ӧ������=��������
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			if(managerNum==performNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/managerNum;
				//����ܼ�Ч�ɼ�
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("����ܳɼ�");
			}
			long e3=System.currentTimeMillis();//��ȡ����ʱ��
			System.out.println("�ܾ��������ʱ�䣺"+(e3-s3)+"ms");//�����������ʱ��
		}
		
		long e=System.currentTimeMillis();//��ȡ����ʱ��
		System.out.println("impl�ж��Ƿ�������۵�����ʱ�䣺"+(e-s)+"ms");//�����������ʱ��
		return 0;
	}
	
	//��Ӽ�Ч���۳�ʼ����
	@Transactional
	public void addInitPerform(){
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//�������ڸ�ʽ
	        String nowTime=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			//��ȡ�ܾ�����
			User GeneralManager = userMapper.findGeneralManager();
			int GMjobId = GeneralManager.getJobId();
			//��ȡ���ž���Ĺ���
			List<User> ManagerList = userMapper.findManager();
			
			//��ʼ���ܾ���Ч����
			int i = performScoreMapper.initGeneralManagerEvaluate(GMjobId,ManagerList,nowTime);
			System.out.println("��ʼ���ܾ���Ч�������ݣ�"+i+"��");
			
			//��ʼ�����ž���Ч����
			int j = performScoreMapper.addMToGMEvaluate(ManagerList,GMjobId,nowTime);
			System.out.println("��ʼ�����ž�����ܾ���Ч�������ݣ�"+j+"��");
			
			//��ʼ�����ž���Բ���Ա����Ч����
			for(int x=0;x<ManagerList.size();x++){
				//ȡ�����ž�����
				String MjobId =String.valueOf(ManagerList.get(x).getJobId());
				//ȡ���þ����ڵ�Ա������
				List<User> StaffList = userMapper.findStaff(MjobId);
				//��Ӳ��ž����Ա���ļ�Ч����
				int k = performScoreMapper.addMToStaffEvaluate(MjobId,StaffList,nowTime);
				System.out.println("��ʼ��"+MjobId+"���ž����Ա����Ч�������ݣ�"+k+"��");
				//���Ա���Բ��ž���ļ�Ч����
				int l = performScoreMapper.addStaffToMEvaluate(StaffList,MjobId,nowTime);
				System.out.println("��ʼ��"+MjobId+"������Ա���Բ��ž���ļ�Ч�������ݣ�"+l+"��");
				for(int y=0;y<StaffList.size();y++){
					//��ȡԱ������
					String SjobId=String.valueOf(StaffList.get(y).getJobId());
					//��ȡ��Ա����ͬ�¹���
					List<User> colleagueList = userMapper.findColleague(SjobId);
					//���Ա����ͬ�µļ�Ч����
					int m = performScoreMapper.addStaffToStaff(SjobId,colleagueList,nowTime);
					System.out.println("��ʼ��"+MjobId+"������Ա����Ա���ļ�Ч�������ݣ�"+m+"��");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("���ݿ�������");
			throw new RuntimeSqlException();
		}
		
	}

}
