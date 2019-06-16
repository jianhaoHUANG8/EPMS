package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.User;
import com.epms.Service.UserService;
import com.epms.Utils.SendMail;


@Controller

public class UserController {
	@Autowired
	private UserService userService;
	

	//@Scheduled(cron="*/10 * * * * ?")
	public void print() {
		System.out.println("�������ݿ�");
		userService.test(444,"444");
	}
	
	@RequestMapping(value="/showAllUser",produces="application/json;charset=utf-8")
	public @ResponseBody String showAllUser(String jobId,String name,String departmentName,int page,int limit){
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<User> eilist=userService.findAllUser(jobId,name,departmentName,before,limit);
		int count=userService.count(jobId,name,departmentName);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@Valid User user,Errors errors,Model model,HttpSession session){
		//����service������ѯ
		user=userService.checkLogin(user.getJobId(),user.getPassword());
		String result="false";
		if(errors.hasErrors()){
			//��ȡ������Ϣ
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//��ӡ�ֶδ�����Ϣ
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
			}
		}else{
			if(user!=null){
				String username=userService.findNameByJobId(user.getJobId());
				session.setAttribute("jobId", user.getJobId());
				session.setAttribute("username", username);
				result="true";
			}else{
				result="false";
			}
		}
		
		return result;
	}
	
	
	@RequestMapping("/outLogin")
	public String outLogin(HttpSession session){
		//ͨ��session.invalidata()������ע����ǰ��session
        session.invalidate();
        return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/addAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String addAccount(@Valid User user,Errors errors,String name,String denameId,String ocnameId){
		String password=user.getPassword();
		JSONObject result = new JSONObject();
		if(errors.hasErrors()){
			//��ȡ������Ϣ
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//��ӡ�ֶδ�����Ϣ
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
				return null;
			}
		}else{
			System.out.print(denameId);
			int jobid=userService.selectLastJobId(denameId)+1;
			//��ѯ�û��Ƿ����
			User addUser=userService.findAccountByJobId(jobid);
			//���ؽ��
			if(addUser==null){
				if(userService.addAccount(jobid,name,denameId,ocnameId, password)==1){
					result.put("status", true);
					result.put("msg", "��ӳɹ�");
				}else{
					result.put("status", false);
					result.put("msg", "���Ŵ���");
				}
				
			}else{
				result.put("status", false);
				result.put("msg", "�˺��Ѵ���");
			}
			
		}
		
		return result.toString();
	}
	
	@RequestMapping(value="/editAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String editAccount(Model model,String jobId,String password,String state,String denameId,String ocnameId){
		int jobid=Integer.parseInt(jobId);
		JSONObject data = new JSONObject();
		if(userService.editAccount(jobid,password,state,denameId,ocnameId)==1){
			data.put("status", true);
			data.put("msg", "�޸ĳɹ�");
		}else{
			data.put("status", false);
			data.put("msg", "�޸�ʧ��");
		}
		return data.toString();
	}
	
	@RequestMapping(value="/resetPassword",produces="application/json;charset=utf-8")
	@ResponseBody
	public String resetPassword(String jobId,String email){
		JSONObject result = new JSONObject();
		//ʵ����һ�������ʼ��Ķ���
		SendMail mySendMail=new SendMail();
		//���������ҵ����û���Ϣ

		User user= userService.getUserByEmail(jobId,email);
		
		if(user!=null) {
			mySendMail.sendMail(email, "��ҵ���¹���ϵͳ���ѣ���������Ϊ��"+user.getPassword());
			result.put("status",true);
			result.put("msg","��ϲ���һ�����ɹ�");
		}else{
			result.put("status",false);
			result.put("msg","�����䲻����");
		}
		return result.toString();
	}
	
}
