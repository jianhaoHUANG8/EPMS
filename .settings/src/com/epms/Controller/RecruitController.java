package com.epms.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Recruit;
import com.epms.Service.RecruitService;
import com.epms.Tools.SendResumeMail;

//��Ƹ�ƻ�����
@Controller
@RequestMapping(value = "RecruitController")
public class RecruitController {
	@Autowired
	private RecruitService recruitService;

	@Autowired
	private Recruit recruit;

	@RequestMapping(value = "/login")
	public String login(Integer jobId, HttpSession session) 
	{
		session.setAttribute("jobId", jobId);
		return "whiteA";
	}
	
	//�ύ��Ƹ�ƻ�
	@RequestMapping(value = "insertRecruit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertRecruit(@Valid Recruit recruit,BindingResult error,HttpSession session)
	{
		if(recruit.getFunctionIntrduce().length()<=0||recruit.getDemand().length()<=0||recruit.getSum()<=0)
		{
			return null;
		}
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
	    recruit.setWriteId(jobId);
	    String result=recruitService.insertRecruit(recruit);
		return result;
	}
	
	//�ϼ���ѯ�¼��ύ����Ƹ�ƻ�
	@RequestMapping(value="/selectAllRecruit",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruit(String occupationId,String departmentId,String status,int page,int limit,HttpSession session)
	{
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<Recruit> recruitlist=recruitService.selectAllRecruit(occupationId,departmentId,status,before, limit, jobId);
		int count=recruitService.countAllRecruit(occupationId,departmentId,status,jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//�����Ƹ�ƻ�
	@RequestMapping(value = "updateRecruitStatus", method = RequestMethod.POST)
	public @ResponseBody String updateRecruitStatus(Recruit recruit) 
	{		
		return recruitService.updateRecruitStatus(recruit);
	}
	
	//�¼���ѯ�Լ��ύ���ϼ���ȫ����Ƹ�ƻ�
	@RequestMapping(value="/selectAllRecruitByWriteId",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruitByWriteId(String departmentId,String occupationId,String status,int page,int limit,HttpSession session){
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<Recruit> recruitlist=recruitService.selectAllRecruitByWriteId(departmentId,occupationId,status,before, limit, jobId);
		int count=recruitService.countSelectAllRecruitByWriteId(departmentId,occupationId,status,jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//Ա����ѯ��Ƹ�ƻ�
	@RequestMapping(value="/selectAllRecruitToEmployee",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruitToEmployee(String occupationId,String departmentId,int page,int limit)
	{
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<Recruit> recruitlist=recruitService.selectAllRecruitToEmployee(occupationId,departmentId,before, limit);
		int count=recruitService.countSelectAllRecruitToEmployee(occupationId,departmentId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}

	

}

