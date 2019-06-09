package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.PerformSum;
import com.hjh.Service.PerformSumService;

@Controller
public class PerformSumController {

	@Autowired
	private PerformSumService performSumService;
	
	@RequestMapping(value="/showAllPerform",produces="application/json;charset=utf-8;")
	@ResponseBody
	public String showAllPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<PerformSum> eilist=performSumService.showAllPerform(jobId,before,limit);
		int count=performSumService.count(jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/showVerifyPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showManagerVerifyPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		String identity=performSumService.findIdentityByJobId(jobId);
		List<PerformSum> eilist=null;
		int count=0;
		if(identity.equals("����")){
			eilist=performSumService.showDirectorVerifyPerform(jobId,before,limit);
			count=performSumService.countDirectorVerifyPerform(jobId);
		}else if(identity.equals("�ܾ���")){
			eilist=performSumService.showGeneralManagerVerifyPerform(jobId,before,limit);
			count=performSumService.countManagerVerifyPerform(jobId);
		}else{//���ž���
			eilist=performSumService.showVerifyPerform(jobId,before,limit);
			count=performSumService.countStaffVerifyPerform(jobId);
		}
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/updateVerifyPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateVerifyPerformYes(HttpSession session,int jobId,String assessDate){
		
		int approverId=Integer.parseInt(session.getAttribute("jobId").toString());

		String result="";
		int i=performSumService.updateVerifyPerform(jobId,assessDate,approverId);
		
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/updateVerifyPerformNo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateVerifyPerformNo(HttpSession session,int jobId,String assessDate){
		//int approverId=Integer.parseInt(session.getAttribute("jobId").toString());
		String result="";
		int num = performSumService.updateVerifyPerformNo(jobId,assessDate);
		if(num==1){
			result="true";
			return result;
		}else{
			result="false";
			return result;
		}
	}
	
	@RequestMapping(value="/showStaffPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showStaffPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		String identity=performSumService.findIdentityByJobId(jobId);
		List<PerformSum> eilist=null;
		int count=0;
		
		if(identity.equals("����")){
			eilist=performSumService.showDirectorStaffPerform(jobId,before,limit);
			count=performSumService.countDirectorStaffPerform(jobId);
		}else if(identity.equals("�ܾ���")){
			eilist=performSumService.showGeneralManagerStaffPerform(jobId,before,limit);
			count=performSumService.countGeneralManagerStaffPerform(jobId);
		}else{//���ž���
			eilist=performSumService.showManagerStaffPerform(jobId,before,limit);
			count=performSumService.countManagerStaffPerform(jobId);
		}
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}
