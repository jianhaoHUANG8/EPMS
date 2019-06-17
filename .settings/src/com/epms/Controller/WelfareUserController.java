package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.User;
import com.epms.Bean.WelfareUser;
import com.epms.Service.WelfareUserService;

@Controller
public class WelfareUserController {

	@Autowired
	private WelfareUserService welfareUserService;
	
	@RequestMapping(value="/showWelfare",produces="application/json;charset=utf-8")
	public @ResponseBody String showWelfare(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<WelfareUser> eilist=welfareUserService.showWelfare(jobId,before,limit);
		int count=welfareUserService.count(jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}
