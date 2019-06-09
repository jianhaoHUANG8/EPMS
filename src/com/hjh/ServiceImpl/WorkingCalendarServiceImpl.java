package com.hjh.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Mapper.WorkingCalendarMapper;
import com.hjh.Service.WorkingCalendarService;

@Service("workingCalendarService")
public class WorkingCalendarServiceImpl implements WorkingCalendarService
{
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	//�����·ݲ�ѯ��������
	@Override
	public int CountWorkSum(String year,String month) 
	{
		return workingCalendarMapper.CountWorkSum(year,month);
	}
	
	//�����·ݲ�ѯ�ڼ�������
	@Override
	public int CountNotWorkSum(String year,String month)
	{
		return workingCalendarMapper.CountNotWorkSum(year,month);
	}

}
