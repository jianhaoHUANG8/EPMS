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

import com.epms.Bean.CultivateRecord;
import com.epms.Service.CultivateRecordService;


@Controller
@RequestMapping(value="CultivateRecordController")
public class CultivateRecordController {
	@Autowired
	private CultivateRecordService cultivateRecordService;
	
	//Ա��������ѵ�ƻ�
	@RequestMapping(value = "insertCultivateRecord", method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertCultivateRecord(int id,HttpSession session) 
	{		
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		String result=cultivateRecordService.insertCultivateRecord(id,jobId); 
		return result;
	}
	
	//Ա����ѯ�Լ���������ѵ�γ�
	@RequestMapping(value = "/selectCultivateRecordByJobId", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectCultivateRecordByJobId(String cultivateId,String status,int page, int limit, HttpSession session) 
	{
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateRecord> cultivateRecordlist = 
				cultivateRecordService.selectCultivateRecordByJobId(cultivateId,status,before, limit, jobId);
		int count = cultivateRecordService.countSelectCultivateRecordByJobId(cultivateId,status,jobId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(cultivateRecordlist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	//�ϼ���ѯ������������ѵ�γ�
	@RequestMapping(value = "/selectAllCultivateRecord", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectAllCultivateRecord(int page,int limit,HttpSession session) 
	{
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int leaderId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateRecord> cultivateRecordlist = 
				cultivateRecordService.selectAllCultivateRecord(before, limit,leaderId);
		int count = cultivateRecordService.countselectAllCultivateRecord(leaderId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(cultivateRecordlist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	//���ͨ������
	@RequestMapping(value = "updateCultivateRecordStatusYes",produces="application/json;charset=utf-8")
	public @ResponseBody String updateCultivateRecordStatusYes(int recordId,String recordStatus,int cultivateId) 
	{
		String result =cultivateRecordService.updateCultivateRecordStatus(recordId,recordStatus,"ͨ��",cultivateId);
		return result;
	}
	
	//��˲�ͨ������
	@RequestMapping(value = "updateCultivateRecordStatusNo",produces="application/json;charset=utf-8")
	public @ResponseBody String updateCultivateRecordStatusNo(int recordId,String recordStatus,int cultivateId) 
	{
		String result =cultivateRecordService.updateCultivateRecordStatus(recordId,recordStatus,"��ͨ��",cultivateId);
		return result;
	}

}
