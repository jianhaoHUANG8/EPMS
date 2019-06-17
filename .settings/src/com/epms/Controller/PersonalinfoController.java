package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Personalinfo;
import com.epms.Service.PersonalinfoService;

@Controller
@RequestMapping(value="PersonalinfoController")
public class PersonalinfoController 
{
	@Autowired
	private PersonalinfoService personalinfoService;
	
	@Autowired 
	private Personalinfo personal;
	
	@RequestMapping(value="/test")
	public String test(int jobId,HttpSession session)
	{
		session.setAttribute("jobId", jobId);
		return "welcome";
	}
	
	@RequestMapping(value="/test2",method=RequestMethod.POST)
	public ModelAndView test2(@Valid @ModelAttribute("command") Personalinfo personal, BindingResult error,ModelAndView mv,HttpSession session)
	{	
		mv.addObject("personal", personal);
		mv.setViewName("../../start");
		return mv;
	}
	
	//�Լ���ѯ������Ϣ
	@RequestMapping(value="/SelectPersonal",method=RequestMethod.GET)
	public ModelAndView SelectPersonal(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		personal=personalinfoService.selectPersonalById(jobId);
		mv.addObject("personalinfo", personal);
		mv.setViewName("selectPersonalinfo");
		return mv;
	}
	
	//�޸ĸ�����Ϣ
	@RequestMapping(value="/updatePersonal",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String updatePersonal(@Valid Personalinfo personalinfo,
			             BindingResult error,Errors errors,HttpSession session)
	{
		if(errors.hasErrors()){
			//��ȡ������Ϣ
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error1:errorList){
				//��ӡ�ֶδ�����Ϣ
				System.err.println("field:"+error1.getField()+"\t"+"msg:"+error1.getDefaultMessage());
			}
		}
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		personalinfo.setJobId(jobId);
		String result=personalinfoService.updatePersonal(personalinfo);
		return result;
	}
	
	//��ѯȫ����Ա��Ϣ
	@RequestMapping(value="/showAllPersonal",produces="application/json;charset=utf-8")
	public @ResponseBody String showAllPersonal(String occupation_id,String job_id,String name,String department_id,
			              int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Personalinfo> personalList=personalinfoService.selectAllPersonal(occupation_id, job_id, name, department_id, before, limit, jobId);
		
		int count=personalinfoService.count(occupation_id, job_id, name, department_id, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(personalList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}
