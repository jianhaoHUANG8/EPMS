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
	
	//查询某个月每个员工的培训天数
	@Override
	public List<TotalData> CountCultivate(String year, String month)
	{
		CalculateDaySum ads=new CalculateDaySum();
		int day,startId,finishId,workDay=0;
		
		//首先把totalData表中所有员工读出来
		List<TotalData> totalDataList=totalDataMapper.selectAllDataSum();
		
		//循环查询员工是否参加了培训，根据培训id查出对应的开始时间和结束时间，来计算对应的天数
		for(int i=0;i<totalDataList.size();i++)
		{
			//计算出员工所参加的培训id
			Integer[] ids=cultivateRecordMapper.selectIdByJobId(totalDataList.get(i).getJobId());
			
			if(ids.length>0)
			{
				//根据id查询出对应的所有培训时间(开始和结束),(1)开始时间和结束时间都在相同某月份
				List<CultivateApply> sameList=cultivateApplyMapper.selectTheSameDateInId(ids, year, month);
				if(sameList!=null)
				{
					for(int j=0;j<sameList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(sameList.get(j).getStartDate(),sameList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
						startId=workingCalendarMapper.selectIdByDate(sameList.get(j).getStartDate());
						finishId=workingCalendarMapper.selectIdByDate(sameList.get(j).getFinishDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, finishId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
						
					}
				}
				//获取到最新数据
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(2)开始时间在某月份而和结束时间不在某月份
				List<CultivateApply> sameStartList=cultivateApplyMapper.selectSameStartDateInId(ids, year, month);
				if(sameStartList!=null)
				{
					String lastDay=workingCalendarMapper.selectMonthLastDate(year,month);
					int lastId=workingCalendarMapper.selectIdByDate(lastDay);
					for(int j=0;j<sameStartList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(sameStartList.get(j).getStartDate(),lastDay);
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(j).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
						startId=workingCalendarMapper.selectIdByDate(sameStartList.get(j).getStartDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, lastId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
					}
				}
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(3)开始时间不在某月份而和结束时间在某月份
				List<CultivateApply> sameFinishList=cultivateApplyMapper.selectSameFinishDateInId(ids, year, month);
				if(sameFinishList!=null)
				{
					String firstDay=workingCalendarMapper.selectMonthFirstDate(year,month);
					int firstId=workingCalendarMapper.selectIdByDate(firstDay);
					for(int j=0;j<sameFinishList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(firstDay,sameFinishList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
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
