package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Vacation;
import com.epms.Service.VacationService;

@Controller
@RequestMapping(value="VacationController")
public class VacationController 
{
	@Autowired
	private VacationService vacationService;
	
	//��ѯȫ������ʣ����Ϣ
	@RequestMapping(value="/selectAllRemain",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllRemain(String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacation> vacationList=vacationService.selectVacationRemain(type, before, limit, jobId);
		int count=vacationService.count(type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(vacationList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//ͨ�����ͺ͹��Ų�ѯ����ʣ����Ϣ
	@RequestMapping(value="selectRemainByType")
	public ModelAndView selectRemainByType(Vacation vacation,ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		vacation.setJobId(jobId);
		
		if(vacationService.selectRemainByVacation(vacation)!=null)
		{
			vacation=vacationService.selectRemainByVacation(vacation);
			mv.addObject("vacation", vacation);
		}
		else
		{
			mv.addObject("vacationMess", "���޼���ʣ����Ϣ��");
			mv.addObject("vacation", null);
		}
		mv.setViewName("selectAllVacationRemain");
		return mv;
	}

}
