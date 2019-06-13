package com.epms.ServiceImpl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.AttendanceRule;
import com.epms.Mapper.AttendanceRuleMapper;
import com.epms.Service.AttendanceRuleService;

@Service("attendanceRuleService")
public class AttendanceRuleServiceImpl implements AttendanceRuleService
{
	@Autowired
	private AttendanceRuleMapper attendanceRuleMapper;
	
	@Autowired
	private AttendanceRule attendanceRule;

	//��ѯ���°�ʱ��
	@Override
	public AttendanceRule selectAttendanceRule() 
	{
		return attendanceRuleMapper.selectAttendanceRule();
	}
	
	// �޸����°�ʱ��
	@Override
	public String updatetAttendanceRuleAll(AttendanceRule attendanceRule) 
	{
		JSONObject result = new JSONObject();
		if (attendanceRuleMapper.updatetAttendanceRuleAll(attendanceRule) > 0) 
		{
			result.put("status", true);
			result.put("message", "�޸ĳɹ���");
		} 
		else 
		{
			result.put("status", false);
			result.put("message", "�޸�ʧ�ܣ�");
		}
		return result.toString();
	}
}
