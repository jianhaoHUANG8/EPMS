package com.epms.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Resume;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.ResumeService;
import com.epms.Tools.SendResumeMail;

@Controller
@RequestMapping(value="ResumeController")
public class ResumeController 
{
	@Autowired
	private ResumeService resumeService;	
	@Autowired 
	private Resume resume;
	@Autowired 
	private ExternalResumeService externalResumeService;
	
	@Autowired 
	private ResumeMapper resumeMapper;
	
	//��ѯȫ����Ա�ļ���
	@RequestMapping(value = "/selectAllResume", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllExternalResume(String departmentId,String occupationId,String status,int page, int limit, HttpSession session) 
	{
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List list = resumeService.selectAllResumeByJobId(departmentId,occupationId,status,before, limit, jobId);
		int count = resumeService.countByJobId(departmentId,occupationId,status,jobId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	// ��˼���
	@RequestMapping(value = "updateAllResume", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody
	String updateAllResume(int id,String interviewDate,String status,String interviewName,String email) 
	{
		resume=new Resume();
		resume.setInterviewDate(interviewDate);
		resume.setInterviewId(id);
		resume.setId(id);
		resume.setStatus(status);
		String result =resumeService.updateAllResume(resume,interviewName);
		JSONObject result1 = new JSONObject();
		//ʵ����һ�������ʼ��Ķ���
		SendResumeMail mySendMail=new SendResumeMail();
		//���������ҵ����û���Ϣ
		ExternalResume user= externalResumeService.getExternalResumeByEmail(email);
		Resume resume2=resumeMapper.selectResumeById(id);
		if(resume2.getApprovalDate()!=null&&resume2.getStatus()=="ͨ��")
		{
				mySendMail.sendResume(email, "��ҵ���¹���ϵͳ���ѣ����ļ�����ͨ��"+"����"+interviewDate+"��������μ�����");
				result1.put("status",true);
				result1.put("msg","���ͳɹ�");
			}else{
				mySendMail.sendResume(email, "��ҵ���¹���ϵͳ���ѣ��ܱ�Ǹ���ļ���û��ͨ������л�������ǹ�˾���Ͽɣ�");
				result1.put("status",false);
				result1.put("msg","�����䲻����");
			}
		return result;
		
	}

}


