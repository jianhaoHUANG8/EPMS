package com.epms.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Vacate;
import com.epms.Service.VacateService;

@Controller
@RequestMapping(value="VacateController")
public class VacateController 
{
	@Autowired
	private VacateService vacateService;
	
	//�ύ�������
	@RequestMapping(value="submitVacateApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String submitVacateApply(@Valid Vacate vacate,BindingResult error,HttpSession session,ModelAndView mv)
	{
		if(error.hasErrors()||vacate.getStartDate().equals("")||vacate.getFinishDate().equals("")||vacate.getReason().equals(""))
		{
			return null;
		}
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		vacate.setJobId(jobId);
		String result=vacateService.insertVacate(vacate);
		session.setAttribute("vacate", vacate);
		return result;
	}
	
	//��ѯ�Լ��ύ��ֱ���ϼ�����������¼(�Լ���ѯ)
	@RequestMapping(value="/selectAllVacateApplyByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllVacateApplyByJobId(String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		int after=page*limit;
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectAllVacateApplyByJobId(state, type, before, after, jobId);
		
		int count=vacateService.count(state, type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//��ѯȫ��ֱ���¼��ύ�������������(�ϼ���ѯ������)
	@RequestMapping(value="/selectAllVacateApply",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllVacateApply(String department_id,String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectAllVacateApply(department_id, state, type, before, limit, jobId);
			
		int count=vacateService.countToLeader(department_id, state, type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//�����������
	@RequestMapping(value="/updateVacateApply",method=RequestMethod.POST)
	public @ResponseBody String updateApply(Vacate vacate)
	{
		String result=vacateService.updateVacate(vacate);
		return result;
	}
	
	//���ݹ��Ž���ѯ����ͨ���������Ϣ
	@RequestMapping(value="/selectPassVacateByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectPassVacateByJobId(int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectPassVacateByJobId(before, limit, jobId);
			
		int count=vacateService.countPassVacateByJobId(jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//���ٴ��� 
	@RequestMapping(value="/cancelVacateApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String cancelVacateApply(Vacate vacate)
	{
		String result=vacateService.cancelVacateApply(vacate);
		return result;
	}

}
