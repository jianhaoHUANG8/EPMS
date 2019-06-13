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

import com.epms.Bean.OccupationAdjust;
import com.epms.Bean.SalaryAdjust;
import com.epms.Service.SalaryAdjustService;

@Controller
@RequestMapping(value="SalaryAdjustController")
public class SalaryAdjustController 
{
	@Autowired
	private SalaryAdjustService salaryAdjustService;
	
	@Autowired
	private SalaryAdjust salaryAdjust;
	
	//�ύн�ʵ��������
	@RequestMapping(value="insertSalaryAdjust",produces="application/json;charset=utf-8")
	public @ResponseBody String insertSalaryAdjust(@Valid SalaryAdjust salaryAdjust,BindingResult error,
			             String passiveName,HttpSession session,ModelAndView mv)
	{
		if(error.hasErrors())
		{
			return null;
		}
		else
		{
			int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
			salaryAdjust.getApply().setWriteId(jobId);
			String result =salaryAdjustService.insertSalaryAdjust(salaryAdjust, passiveName);
			return result;
		}
	}
	
	//��ѯȫ���ύ��ֱ���ϼ���н�ʵ���������Ϣ
	@RequestMapping(value="/selectAllSalaryAdjustByWriteId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllSalaryAdjustByWriteId(String department_id,String state,String salaryadjust_type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<SalaryAdjust> salaryAdjustList=salaryAdjustService.selectAllSalaryAdjustByWriteId(department_id, state, salaryadjust_type, before, limit, jobId);
			
		int count=salaryAdjustService.countByWriteId(department_id, state, salaryadjust_type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(salaryAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//�ϼ���ѯȫ��ֱ�������ύ��н�ʵ���������Ϣ
	@RequestMapping(value="/selectAllSalaryAdjust",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllSalaryAdjust(String department_id,String state,String salaryadjust_type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<SalaryAdjust> salaryAdjustList=salaryAdjustService.selectAllSalaryAdjust(department_id, state, salaryadjust_type, before, limit, jobId);
			
		int count=salaryAdjustService.countAllSalaryAdjust(department_id, state, salaryadjust_type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(salaryAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//����н�ʵ�������
	@RequestMapping(value="/updateSalaryAdjust",method=RequestMethod.POST)
	public @ResponseBody String updateSalaryAdjust(SalaryAdjust salaryAdjust,String state)
	{
		System.out.println(salaryAdjust);
		String result=salaryAdjustService.updateSalaryAdjust(salaryAdjust,state);
		return result;
	}
	
	//���ݹ��Ų�ѯ��ص�н�ʵ�����¼
	@RequestMapping(value="/selectAllSalaryAdjustByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllSalaryAdjustByJobId(String state,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<SalaryAdjust> salaryAdjustList=salaryAdjustService.selectAllSalaryAdjustByJobId(state, before, limit, jobId);
			
		int count=salaryAdjustService.countByJobId(state, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(salaryAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}
