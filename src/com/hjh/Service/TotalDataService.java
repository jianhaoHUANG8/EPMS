package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.TotalData;


public interface TotalDataService 
{		
	//根据查询所有员工当月的迟到，早退，请假，加班，缺勤数据
	List<TotalData> selectMonthTotalData(String year,String month);
}
