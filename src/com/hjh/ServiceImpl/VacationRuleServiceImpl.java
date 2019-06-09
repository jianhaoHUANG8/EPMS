package com.hjh.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.VacationRule;
import com.hjh.Mapper.VacationRuleMapper;
import com.hjh.Service.VacationRuleService;

@Service("vacationRuleService")
public class VacationRuleServiceImpl implements VacationRuleService
{
	@Autowired
	private VacationRuleMapper vacationRuleMapper;
	
	@Autowired
	private VacationRule vacationeRule;
	
	//�޸ļ��ڹ涨����
	@Override
	public String updatetVacationRule(VacationRule vacationRule) 
	{
		JSONObject result =new JSONObject();
		if(vacationRule.getDay()<0)
		{
			return null;
		}
		else
		{
			if(vacationRuleMapper.updatetVacationRule(vacationRule)>0)
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

	//��ѯ���ڹ涨����
	@Override
	public List<VacationRule> selectVacationRule(int before,int after) 
	{
		return vacationRuleMapper.selectVacationRule(before, after);
	}

	@Override
	public int count() 
	{
		return vacationRuleMapper.count();
	}

}
