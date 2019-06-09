package com.hjh.Controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjh.Service.WorkingCalendarService;

@Controller
@RequestMapping(value="WorkingCalendarController")
public class WorkingCalendarController 
{
	@Autowired
	private WorkingCalendarService workingCalendarService;
	
	
	//�����·ݲ�ѯ��Ӧ���������ͽڼ�������
	@RequestMapping(value="/selectWorkingOrNotSum")
	public void selectWorkingOrNotSum()
	{	
		Calendar a=Calendar.getInstance();
		String year=String.valueOf(a.get(Calendar.YEAR));
		String month=String.valueOf(a.get(Calendar.MONTH)+1);
		System.out.println("������month��WorkSum"+workingCalendarService.CountWorkSum(year,month));
		System.out.println("������month��NotWorkSum"+workingCalendarService.CountNotWorkSum(year,month));
	}
	
	
	
	
}
