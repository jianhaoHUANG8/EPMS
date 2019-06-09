package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.Apply;
import com.hjh.Bean.OccupationAdjust;
import com.hjh.Service.OccupationAdjustService;

@Controller
@RequestMapping(value="OccupationAdjustController")
public class OccupationAdjustController 
{
	@Autowired
	private OccupationAdjustService occupationAdjustService;
	
	//�ύн�ʵ��������
	@RequestMapping(value="insertOccupationAdjust",produces="application/json;charset=utf-8")
	public @ResponseBody String insertOccupationAdjust(@Valid OccupationAdjust occupationAdjust,BindingResult error,String passiveName,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		if(error.hasErrors())
		{
			return null;
		}
		else
		{
			occupationAdjust.getApply().setWriteId(jobId);
			String result =occupationAdjustService.insertOccupationAdjust(occupationAdjust, passiveName);
			return result;
		}
	}
	
	//��ѯȫ���ύ��ֱ���ϼ���ְλ����������Ϣ
	@RequestMapping(value="/selectAllOccupationAdjustByWriteId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjustByWriteId(String pre_departmentid,
		   String state,String type, int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllOccupationAdjustByWriteId(pre_departmentid, state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countByWriteId(pre_departmentid, state, type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//�ϼ���ѯȫ��ֱ�������ύ��ְλ����������Ϣ
	@RequestMapping(value="/selectAllOccupationAdjuest",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjuest(String pre_departmentid,String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllSalaryAdjust(pre_departmentid, state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countAllOccupationAdjust(pre_departmentid, state, type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//����н�ʵ�������
	@RequestMapping(value="/updateOccupationAdjuest",method=RequestMethod.POST)
	public @ResponseBody String updateSalaryAdjust(OccupationAdjust occupationAdjust,String state)
	{
		String result=occupationAdjustService.updateOccupationAdjust(occupationAdjust, state);
		return result;
	}
	
	//���ݹ��Ų�ѯ��ص�ְλ������¼
	@RequestMapping(value="/selectAllOccupationAdjustByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjustByJobId(String state,String type,
			             int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllOccupationAdjustByJobId(state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countByJobId(state, type, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}
