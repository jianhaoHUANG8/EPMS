package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Apply;
import com.epms.Bean.Attendance;
import com.epms.Bean.SocialSecurity;
import com.epms.Service.ApplyService;
import com.epms.Service.SocialSecurityService;

@Controller
@RequestMapping(value="SocialSecurityController")
public class SocialSecurityController 
{
	@Autowired
	private SocialSecurityService socialSecurityService;
	
	@Autowired
	private SocialSecurity socialSecurity;
	
	//��ѯ�Լ�ȫ��������Ϣ
	@RequestMapping(value="/selectSocialSecurity",produces="application/json;charset=utf-8")
	public @ResponseBody String selectSocialSecurity(String securityType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		socialSecurity.setJobId(jobId);
		List<SocialSecurity> socialSecurityList=socialSecurityService.selectSocialSecurityById(securityType, before, limit, jobId);
		
		int count=socialSecurityService.count(securityType, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(socialSecurityList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	//���ݱ������Ͳ�ѯ���չ�����Ϣ
	@RequestMapping(value="selectSocialSecurityByTypeAndId")
	public ModelAndView selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		socialSecurity.setJobId(jobId);	
		List<SocialSecurity> socialSecurityList=socialSecurityService.selectSocialSecurityByTypeAndId(socialSecurity);
		if(socialSecurityList.isEmpty())
		{
			mv.addObject("selectSocialSecurityByTypeAndId", "�������鱣����Ϣ");
			mv.setViewName("socialSecurity");
		}
		else
		{
			mv.addObject("socialSecurityList", socialSecurityList);
			mv.setViewName("selectAllSocialSecurity");
		}
		return mv;
	}
	
}
