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

import com.epms.Bean.FeedBack;
import com.epms.Service.FeedBackService;

@Controller
@RequestMapping(value="FeedBackController")
public class FeedBackController 
{
	@Autowired
	private FeedBackService feedBackService;
	
	//���뷴����Ϣ
	@RequestMapping(value="/insertFeedbackInfo",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertFeedbackInfo(@Valid FeedBack feedback,BindingResult error,HttpSession session)
	{
		if(error.hasErrors())
		{
			return null;
		}
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		feedback.setJobId(jobId);
		String result=feedBackService.insertFeedbackInfo(feedback);
		return result;
	}
	
	//�ϼ���ѯ�¼��ύ�����з�����Ϣ
	@RequestMapping(value="/selectAllFeedBack",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllFeedBack(String state,String department_id,String feedback_type,
			                                      int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<FeedBack> feedBackList=feedBackService.selectAllFeedback(state, department_id, feedback_type, before, limit, jobId);
			
		int count=feedBackService.count(state, department_id, feedback_type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(feedBackList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//����������Ϣ
	@RequestMapping(value="/updateFeedBack",method=RequestMethod.POST)
	public @ResponseBody String updateFeedBack(FeedBack feedback)
	{
		String result=feedBackService.updateFeedback(feedback);
		return result;
	}
	
	//�Լ���ѯ�ύ���ϼ���ȫ��������Ϣ
	@RequestMapping(value="/selectAllFeedBackByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllFeedBackByJobId(String state,String feedbackType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<FeedBack> feedBackList=feedBackService.selectAllFeedBackByJobId(state, feedbackType, before, limit, jobId);
			
		int count=feedBackService.countByJobId(state, feedbackType, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(feedBackList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}
