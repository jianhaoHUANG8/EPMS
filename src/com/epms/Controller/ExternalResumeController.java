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
import com.epms.Service.ExternalResumeService;
import com.epms.Service.InteralResumeService;
import com.epms.Tools.SendResumeMail;


//�ⲿ��Ա�������
@Controller
@RequestMapping(value="ExternalResumeController")
public class ExternalResumeController 
{

	@Autowired
	private InteralResumeService interalResumeService;
	
	@Autowired
	private ExternalResumeService externalResumeService;
	
	@Autowired
	private ExternalResume externalResume;
	
	//�����ύ
	@RequestMapping(value = "insertExternalResume", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertExternalResume(@Valid ExternalResume externalResume,BindingResult error,HttpSession session)
	{
		if(error.hasErrors())
		{
			return null;
		}
		String result=externalResumeService.insertExternalResume(externalResume);
		return result;
	}
	
	//��ѯ�ⲿ��Ա�ļ���
	@RequestMapping(value="/selectAllExternalResume",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllExternalResume(String departmentId,String occupationId,String status,int page,int limit,HttpSession session)
	{
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<ExternalResume> externalResumelist=externalResumeService.selectAllExternalResume(departmentId,occupationId,status,before,limit,jobId);			
		int count=externalResumeService.countSelectAllExternalResume(departmentId,occupationId,status,jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(externalResumelist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}

	
}

