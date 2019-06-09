package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.CultivateApply;
import com.hjh.Bean.TotalData;
import com.hjh.Mapper.CultivateApplyMapper;
import com.hjh.Mapper.CultivateRecordMapper;
import com.hjh.Mapper.TotalDataMapper;
import com.hjh.Mapper.WorkingCalendarMapper;
import com.hjh.Service.CultivateRecordService;
import com.hjh.Utils.CalculateDaySum;

@Service(value = "cultivateRecordService")
public class CultivateRecordServiceImpl implements CultivateRecordService 
{
	@Autowired
	private CultivateApplyMapper cultivateApplyMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	@Autowired
	private CultivateRecordMapper cultivateRecordMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	//��ѯĳ����ÿ��Ա������ѵ����
	@Override
	public List<TotalData> CountCultivate(String year, String month)
	{
		CalculateDaySum ads=new CalculateDaySum();
		int day,startId,finishId,workDay=0;
		
		//���Ȱ�totalData��������Ա��������
		List<TotalData> totalDataList=totalDataMapper.selectAllDataSum();
		
		//ѭ����ѯԱ���Ƿ�μ�����ѵ��������ѵid�����Ӧ�Ŀ�ʼʱ��ͽ���ʱ�䣬�������Ӧ������
		for(int i=0;i<totalDataList.size();i++)
		{
			//�����Ա�����μӵ���ѵid
			Integer[] ids=cultivateRecordMapper.selectIdByJobId(totalDataList.get(i).getJobId());
			
			if(ids.length>0)
			{
				//����id��ѯ����Ӧ��������ѵʱ��(��ʼ�ͽ���),(1)��ʼʱ��ͽ���ʱ�䶼����ͬĳ�·�
				List<CultivateApply> sameList=cultivateApplyMapper.selectTheSameDateInId(ids, year, month);
				if(sameList!=null)
				{
					for(int j=0;j<sameList.size();j++)
					{
						//�������ѵ����,���������ݿ�
						day=ads.calculate(sameList.get(j).getStartDate(),sameList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//���������ȱ��������ȥ��������Ĵ���ѵ�ڼ乤���յ�����
						startId=workingCalendarMapper.selectIdByDate(sameList.get(j).getStartDate());
						finishId=workingCalendarMapper.selectIdByDate(sameList.get(j).getFinishDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, finishId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
						
					}
				}
				//��ȡ����������
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(2)��ʼʱ����ĳ�·ݶ��ͽ���ʱ�䲻��ĳ�·�
				List<CultivateApply> sameStartList=cultivateApplyMapper.selectSameStartDateInId(ids, year, month);
				if(sameStartList!=null)
				{
					String lastDay=workingCalendarMapper.selectMonthLastDate(year,month);
					int lastId=workingCalendarMapper.selectIdByDate(lastDay);
					for(int j=0;j<sameStartList.size();j++)
					{
						//�������ѵ����,���������ݿ�
						day=ads.calculate(sameStartList.get(j).getStartDate(),lastDay);
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(j).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//���������ȱ��������ȥ��������Ĵ���ѵ�ڼ乤���յ�����
						startId=workingCalendarMapper.selectIdByDate(sameStartList.get(j).getStartDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, lastId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
					}
				}
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(3)��ʼʱ�䲻��ĳ�·ݶ��ͽ���ʱ����ĳ�·�
				List<CultivateApply> sameFinishList=cultivateApplyMapper.selectSameFinishDateInId(ids, year, month);
				if(sameFinishList!=null)
				{
					String firstDay=workingCalendarMapper.selectMonthFirstDate(year,month);
					int firstId=workingCalendarMapper.selectIdByDate(firstDay);
					for(int j=0;j<sameFinishList.size();j++)
					{
						//�������ѵ����,���������ݿ�
						day=ads.calculate(firstDay,sameFinishList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//���������ȱ��������ȥ��������Ĵ���ѵ�ڼ乤���յ�����
						finishId=workingCalendarMapper.selectIdByDate(sameFinishList.get(j).getFinishDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(firstId, finishId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
					}
				}
			}
		}
		return totalDataMapper.selectAllDataSum();
	}
	

}
