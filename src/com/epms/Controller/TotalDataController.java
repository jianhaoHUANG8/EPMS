package com.epms.Controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epms.Service.TotalDataService;

@Controller
@RequestMapping(value="TotalDataController")
public class TotalDataController 
{
	@Autowired
	private TotalDataService totalDataService;
	
	
	//���ݲ�ѯ����Ա�����µĳٵ������ˣ���٣��Ӱ࣬ȱ������
	@RequestMapping(value="/selectMonthTotalData")
	public void selectMonthTotalData()
	{	
		//����ȱ��û����
		Calendar a=Calendar.getInstance();
		String year=String.valueOf(a.get(Calendar.YEAR));
		String month=String.valueOf(a.get(Calendar.MONTH)+1);
		System.out.println(totalDataService.selectMonthTotalData(year,month));
	}
	
	
	
	
}
