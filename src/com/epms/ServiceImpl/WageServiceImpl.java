package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.PerformSum;
import com.epms.Bean.StaffWage;
import com.epms.Bean.TotalData;
import com.epms.Bean.Wage;
import com.epms.Mapper.PerformSumMapper;
import com.epms.Mapper.WageMapper;
import com.epms.Service.WageService;
@Service("wageService")
public class WageServiceImpl implements WageService{

	@Autowired
	private WageMapper wageMapper;
	
	@Autowired
	private PerformSumMapper performsumMapper;
	
	//�鿴���˹���
	public List<Wage> showWageByJobId(int jobId, int before, int after) {
		return wageMapper.showWageByJobId(jobId,before,after);
	}

	//�鿴���˹�������
	public int countByJobId(int jobId) {
		return wageMapper.countByJobId(jobId);
	}

	//���㹤��
	public void countStaffWage(List<TotalData> totalData) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//�������ڸ�ʽ
        String wageTime=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy");//�������ڸ�ʽ
        String year=df3.format(new Date());
        SimpleDateFormat df2 = new SimpleDateFormat("M");//�������ڸ�ʽ
        String nowMonth=df2.format(new Date());
        int lastMonth=Integer.parseInt(nowMonth)-1;
        String asseaaDate="";
        if(lastMonth<10){
        	asseaaDate=year+"0"+String.valueOf(lastMonth);
        }else{
        	asseaaDate=year+String.valueOf(lastMonth);
        }
		for(int i = 0;i<totalData.size();i++){
			int jobId = totalData.get(i).getJobId();//��ȡ����
			
			Wage wage=wageMapper.selectWage(jobId,wageTime);//��ѯ�Ƿ��й��ʼ�¼jobId,wageTime
			if(wage!=null){
				StaffWage staffWage = wageMapper.selectWageByJobId(jobId);//��ѯ�Ƿ��л�������
				if(staffWage!=null){
					double basicWage=staffWage.getWage();//��ȡ��������
					double halHourfWage=(basicWage/21.75)/16;//��Сʱ����
					double taxAmount=basicWage-5000;
					double wageTax=0;
					if(basicWage<5000){
						wageTax=0;
						taxAmount=0;
					}else if(basicWage>5000&&basicWage<8000){
						wageTax=taxAmount*0.03;
					}else if(basicWage>8001&&basicWage<17000){
						wageTax=taxAmount*0.1;
					}else if(basicWage>17001&&basicWage<30000){
						wageTax=taxAmount*0.2;
					}else if(basicWage>30001&&basicWage<40000){
						wageTax=taxAmount*0.25;
					}else if(basicWage>40001&&basicWage<60000){
						wageTax=taxAmount*0.3;
					}else if(basicWage>60001&&basicWage<85000){
						wageTax=taxAmount*0.35;
					}else if(basicWage>85001){
						wageTax=taxAmount*0.45;
					}
					if(totalData.get(i).getTotalLately()>3||totalData.get(i).getTotalEarlyleave()>3){//�ٵ����˶���3��
						double LatelyEarly=(totalData.get(i).getTotalLately()+totalData.get(i).getTotalEarlyleave())*50;//�ٵ�����
						double vacate=(basicWage/21.75)*totalData.get(i).getTotalVacate();//���
						double workOT = halHourfWage*totalData.get(i).getTotalWorkOverTime();//ƽʱ�Ӱ��
						double notworkOT=halHourfWage*totalData.get(i).getTotalWorkOverTime()*3;//�ڼ��ռӰ��
						double absence=(basicWage/21.75)*totalData.get(i).getTotalAbsence();//ȱ��
						absence=absence-LatelyEarly-vacate;//ȱ��
						double overTimePay=workOT+notworkOT;//�Ӱ��
						double socialSecurity=basicWage*0.097;//�籣
						double housingFund=basicWage*0.08;//ס��������
						double sum=basicWage+overTimePay-absence-socialSecurity-housingFund-wageTax;//ʵ�ʹ���
						wageMapper.addWage(jobId,wageTime,basicWage,overTimePay,0,0,0,absence,socialSecurity,housingFund,0,0,wageTax,taxAmount,sum);
					}else{
						double LatelyEarly=(totalData.get(i).getTotalLately()+totalData.get(i).getTotalEarlyleave())*50;//�ٵ�����
						double vacate=(basicWage/21.75)*totalData.get(i).getTotalVacate();//���
						double workOT = halHourfWage*totalData.get(i).getTotalWorkOverTime();//ƽʱ�Ӱ��
						double notworkOT=halHourfWage*totalData.get(i).getTotalWorkOverTime()*3;//�ڼ��ռӰ��
						double absence=(basicWage/21.75)*totalData.get(i).getTotalAbsence();//ȱ��
						absence=absence-LatelyEarly-vacate;//ȱ��
						double overTimePay=workOT+notworkOT;//�Ӱ��
						double socialSecurity=basicWage*0.097;//�籣
						double housingFund=basicWage*0.08;//ס��������
						double sum=basicWage+overTimePay-absence-socialSecurity-housingFund-wageTax;//ʵ�ʹ���
						PerformSum performSum=performsumMapper.selectPerform(jobId,"�¶�����",asseaaDate);
						double performAllowance=basicWage*0.2*performSum.getScore()/100;
						wageMapper.addWage(jobId,wageTime,basicWage,overTimePay,0,0,performAllowance,absence,socialSecurity,housingFund,0,0,wageTax,taxAmount,sum);
					}
				}else{
					System.out.println(jobId+"û�л�������");
				}
			}else{
				System.out.println(jobId+"���ʼ�¼�Ѵ���");
			}
			
		}
	}

	//�鿴��Ҫ������˵ļ�¼
	public List<Wage> showVerifyWage(int before, int limit) {
		return wageMapper.showVerifyWage(before,limit);
	}

	//�鿴��Ҫ������˵ļ�¼����
	public int countVerifyWage() {
		return wageMapper.countVerifyWage();
	}

	
	
}
