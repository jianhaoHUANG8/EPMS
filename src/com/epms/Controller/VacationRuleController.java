package com.epms.Controller;

import java.util.List;

import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.VacationRule;
import com.epms.Service.VacationRuleService;

@Controller
@RequestMapping(value="VacationRuleController")
public class VacationRuleController 
{
	@Autowired
	private VacationRuleService vacationRuleService;
	
	@Autowired
	private VacationRule vacationRule;
	
	//�޸����°�涨ʱ��
	@RequestMapping(value="/updateVacationRule",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String updateVacationRule(@Valid VacationRule vacationRule,BindingResult error)
	{
		if(error.hasErrors())
		{
			return null;
		}
		String result=vacationRuleService.updatetVacationRule(vacationRule);
		return result;
	}
	
	//��ѯ���°�涨ʱ��
	@RequestMapping(value="/selectVacationRule",produces="application/json;charset=utf-8")
	public @ResponseBody String selectVacationRule(int page,int limit)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<VacationRule> vacationRuleList=vacationRuleService.selectVacationRule(before, limit);
			
		int count=vacationRuleService.count();
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(vacationRuleList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}
