package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.PerformSum;
import com.hjh.Mapper.PerformScoreMapper;
import com.hjh.Mapper.PerformSumMapper;
import com.hjh.Service.PerformSumService;
@Service("performSumService")
public class PerformSumServiceImpl implements PerformSumService{

	@Autowired
	private PerformSumMapper performSumMapper;
	@Autowired
	private PerformScoreMapper performScoreMapper;


	//��ѯ���˵ļ�Ч���۵ļ�¼
	public List<PerformSum> showAllPerform(int jobId, int before, int after) {
		return performSumMapper.showAllPerform(jobId,before,after);
	}

	//����������
	public int count(int jobId) {
		return performSumMapper.count(jobId);
	}
	
	//��ʾ��������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showVerifyPerform(jobId,before,after);
	}
	
	//��ȡ����Ĳ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countStaffVerifyPerform(int jobId){
		return performSumMapper.countStaffVerifyPerform(jobId);
	}
	
	//��˼�Ч���ۼ�¼
	public int updateVerifyPerform(int jobId,String assessDate,int approverId){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        String nowTime=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		return performSumMapper.updateVerifyPerform(jobId,nowTime, assessDate, approverId);
	}
	
	//��ȡ�ܾ�����Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showGeneralManagerVerifyPerform(jobId,before,after);
	}
	
    //��ȡ�ܾ�������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countManagerVerifyPerform(int jobId){
		return performSumMapper.countManagerVerifyPerform(jobId);
	}
	
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼
	public List<PerformSum> showDirectorVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showDirectorVerifyPerform(jobId,before,after);
	}
	
	//��ȡ������Ҫ��˵ļ�Ч���ۼ�¼����
	public int countDirectorVerifyPerform(int jobId){
		return performSumMapper.countDirectorVerifyPerform(jobId);
	}
	
	//ͨ�����Ż�ȡ��ݣ��ܾ������ž������£�
	public String findIdentityByJobId(int jobId){
		return performSumMapper.findIdentityByJobId(jobId);
	}
	
	//��Ч���۲�ͨ��
	public int updateVerifyPerformNo(int jobId, String assessDate){
		//ɾ���ܼ�Ч���۵ļ�¼
		int d=performSumMapper.deletePerformSum(jobId, assessDate);
		//����Ա���Ը�Ա���ļ�Ч��state��Ϊ������
		int u=performScoreMapper.updatePerformScoreState(jobId, assessDate);
		//Ӧ������
		int peopleNum=0;
		if(performScoreMapper.checkStaff(jobId)==0){//��ѯ���������Ƿ�Ϊ��ְͨԱ
			//Ӧ������=ͬ����+���ž���
			peopleNum=performScoreMapper.checkPeopleNum(jobId)-1;
			
		}else if(performScoreMapper.checkManager(jobId)==1){//��ѯ���������Ƿ�Ϊ���ž���
			//Ӧ������=����������+�ܾ���
			peopleNum=performScoreMapper.checkPeopleNum(jobId);
			
		}else if(performScoreMapper.checkGeneralManager(jobId).equals("�ܾ���")){//��ѯ���������Ƿ�Ϊ�ܾ���
			//Ӧ������=���ž���
			peopleNum=performScoreMapper.checkManagerNum()-1;

		}
		System.out.println("d:"+d+" u:"+u+" peopleNum"+peopleNum+" sum:"+(d+u-peopleNum));
		System.out.println("ִ��no");
		return d+u-peopleNum;
	}
	
	//��ȡ���²�ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showDirectorStaffPerform(int jobId,int before,int after){
		return performSumMapper.showDirectorStaffPerform(jobId,before,after);
	}
	//��ȡ���²�ѯԱ����Ч���ۼ�¼����
	public int countDirectorStaffPerform(int jobId){
		return performSumMapper.countDirectorStaffPerform(jobId);
	}
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showGeneralManagerStaffPerform(int jobId,int before,int after){
		return performSumMapper.showGeneralManagerStaffPerform(jobId,before,after);
	}
	//��ȡ�ܾ����ѯԱ����Ч���ۼ�¼����
	public int countGeneralManagerStaffPerform(int jobId){
		return performSumMapper.countGeneralManagerStaffPerform(jobId);
	}
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼
	public List<PerformSum> showManagerStaffPerform(int jobId,int before,int after){
		return performSumMapper.showManagerStaffPerform(jobId,before,after);
	}
	//��ȡ���ž����ѯԱ����Ч���ۼ�¼����
	public int countManagerStaffPerform(int jobId){
		return performSumMapper.countManagerStaffPerform(jobId);
	}
}
