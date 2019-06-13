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

import com.epms.Bean.Communication;
import com.epms.Service.CommunicationService;

@Controller
@RequestMapping(value="CommunicationController")
public class CommunicationController 
{
	@Autowired
	private Communication communication;
	
	@Autowired
	private CommunicationService communicationService;
	
	//��ѯȫ��ͨѶ¼
	@RequestMapping(value="/selectAllCom",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllCom(String jobId,String name,String departmentId,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ����
		List<Communication> comList=communicationService.selectAllCommunication(departmentId, jobId, name, before, limit);
		
		int count=communicationService.count(departmentId, jobId, name);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(comList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//���ݹ��Ų�ѯͨѶ��Ϣ
	@RequestMapping(value="/SelectCommunicationByJobId",method=RequestMethod.GET)
	public ModelAndView SelectCommunicationByJobId(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		communication=communicationService.selectByJobId(jobId);
		mv.addObject("communication", communication);
		mv.setViewName("editCommunication");
		return mv;
	}
	
	// �޸ĸ���ͨѶ��Ϣ
	@RequestMapping(value="/updateCommunication",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String updateCommunication(@Valid Communication communication, BindingResult error,HttpSession session)
	{
		if(error.hasErrors())
		{
			return null;
		}
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		communication.setJobId(jobId);
		String result=communicationService.updateCommunication(communication);
		return result;
	}

}
