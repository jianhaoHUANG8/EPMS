package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.TotalData;
import com.hjh.Mapper.TotalDataMapper;
import com.hjh.Service.AttendanceService;
import com.hjh.Service.CultivateRecordService;
import com.hjh.Service.TotalDataService;
import com.hjh.Service.VacateService;

@Service("totalDataService")
public class TotalDataImpl implements TotalDataService
{
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	@Autowired
	private VacateService vacateService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private CultivateRecordService cultivateRecordService;
	
	//���ݲ�ѯ����Ա�����µĳٵ������ˣ���٣��Ӱ࣬ȱ�ڣ���ѵ����
	@Override
	public List<TotalData> selectMonthTotalData(String year,String month) 
	{
		if(Integer.parseInt(month)<9)
		{
			String str="0";
			month=str+(month);
		}
		vacateService.selectAllVacateSum(year,month);
		attendanceService.selectAllSumDataByInDate(year,month);
		attendanceService.selectAllSumAbsenceByInDate(year, month);
		return cultivateRecordService.CountCultivate(year, month);
	}

}
