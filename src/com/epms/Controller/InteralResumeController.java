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

import com.epms.Bean.InteralResume;
import com.epms.Service.InteralResumeService;

//�ڲ���Ա�������
@Controller
@RequestMapping(value = "InteralResumeController")
public class InteralResumeController {
	@Autowired
	private InteralResumeService interalResumeService;

	@Autowired
	private InteralResume interalResume;
	
	//�ύ����
	@RequestMapping(value = "insertInteralResume",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertInteralResume(@Valid InteralResume interalResume,BindingResult error,HttpSession session) 
	{		
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		interalResume.setJobId(jobId);
		if(error.hasErrors())
		{
			return null;
		}
		String result=interalResumeService.insertInteralResume(interalResume);
		return result;
	}
	
	@RequestMapping(value = "/login")
	public String login(Integer jobId, HttpSession session) 
	{
		session.setAttribute("jobId", jobId);
		return "whiteA";
	}

	//�ڲ���Ա��ѯ�Լ��ļ���
	@RequestMapping(value="/selectInteralResumeByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectInteralResumeByJobId(String departmentId,String occupationId,String status,int page,int limit,HttpSession session)
	{
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<InteralResume> interalResumeList=interalResumeService.selectInteralResumeByJobId(departmentId,occupationId,status,before, limit, jobId);
		int count=interalResumeService.countByJobId(departmentId,occupationId,status,jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(interalResumeList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	//���ž�����ܾ����ѯ������Ϣ
	@RequestMapping(value = "/selectAllInteralResume", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectAllInteralResume(String departmentId,String occupationId,String status,int page, int limit, HttpSession session) 
	{
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<InteralResume> recruitlist = interalResumeService
				.selectAllInteralResume(departmentId,occupationId,status,before, limit, jobId);
		int count = interalResumeService.countAllInteralResume(departmentId,occupationId,status,jobId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(recruitlist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

}
