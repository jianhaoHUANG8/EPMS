package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.Attendance;
import com.hjh.Service.AttendanceService;

@Controller
@RequestMapping(value="AttendanceController")
public class AttendanceController 
{
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private Attendance attendance;
	
	//�ϰ��
	@RequestMapping(value="/CheckIn",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String CheckIn(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		String checkMess=attendanceService.insertInAttendance(jobId);
		JSONObject result = new JSONObject();
		result.put("status", true);
		result.put("msg", checkMess);
		return result.toString();
	}
	
	//�°��
	@RequestMapping(value="/CheckOut",produces="application/json;charset=utf-8")
	@ResponseBody
	public String CheckOut(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		String checkMess=attendanceService.insertOutAttendance(jobId);
		JSONObject result = new JSONObject();
		result.put("status", true);
		result.put("msg", checkMess);
		return result.toString();
	}
	
	//��ѯ���ݹ��Ų�ѯȫ������Ϣ
	@RequestMapping(value="/SelectAllAttendance",produces="application/json;charset=utf-8")
	public @ResponseBody String SelectAllAttendance(String today,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Attendance> attendanceList=attendanceService.selectAllById(before,limit,jobId,today);
		
		int count=attendanceService.count(jobId, today);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(attendanceList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	/*//ģ����ѯ�ܹ��ٵ������ˡ��Ӱ�ʱ����
	@RequestMapping(value="/selectAttendanceSumDataByDate",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAttendanceSumDataByDate(String startDate,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		int after=page*limit;
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<Attendance> attendanceList=attendanceService.selectSumDataByDate(before, after, startDate);
		
		int count=attendanceService.countSumDataByDate(startDate);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(attendanceList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}*/
	
}
