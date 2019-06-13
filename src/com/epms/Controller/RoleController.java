package com.epms.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.Role;
import com.epms.Service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/showRightsGroups",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRightsGroups(int page,int limit){
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<Role> eilist=roleService.showRightsGroups(before,limit);
		int count=roleService.count();
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/addRightsGroups",method=RequestMethod.POST)
	@ResponseBody
	public String addRightsGroups(@Valid Role role,Errors errors,HttpSession session){
		String result="false";
		//��ѯȨ�����Ƿ����
		int addRole=roleService.findGroupsByName(role.getName());
		if(errors.hasErrors()){
			//��ȡ������Ϣ
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//��ӡ�ֶδ�����Ϣ
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
				return null;
			}
		}else{
			if(addRole==0){
				//��ȡ��ǰ����
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		        String nowTime=df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
		        //��ȡ����ߵĹ���
		        int createId=Integer.parseInt(session.getAttribute("jobId").toString());
		        //���Ȩ����
				roleService.addRightsGroups(role.getName(),role.getContent(),createId,nowTime);
				result="true";
			}else{
				result="false";
			}
		}
		return result;
	}
	
	@RequestMapping(value="/editRightsGroups",method=RequestMethod.POST)
	@ResponseBody
	public String editRightsGroups(@Valid Role role,Errors errors,String oldName){
		String result="false";
		if(errors.hasErrors()){
			//��ȡ������Ϣ
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//��ӡ�ֶδ�����Ϣ
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
			}
			return null;
		}else{
			//����Ȩ����
			int i=roleService.editRightsGroups(role.getName(),role.getContent(),oldName);
			if(i==1){
				result="true";
			}else{
				result="false";
			}
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/showRightsGroupsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRightsGroupsPeople(String id,int page,int limit){
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<String> eilist=roleService.showRightsGroupsPeople(id,before,limit);
		int count=roleService.countPeople(id);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/deleteRightsGroupsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteRightsGroupsPeople(int jobId,String rName){
		String result="false";
		int i=roleService.deleteRightsGroupsPeople(jobId,rName);
		if(i==1){
			result="true";
		}else{
			result="false";
		}
		return result;
	}
}
