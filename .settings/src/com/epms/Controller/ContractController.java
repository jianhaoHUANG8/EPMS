package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Attendance;
import com.epms.Bean.Communication;
import com.epms.Bean.Contract;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Service.CommunicationService;
import com.epms.Service.ContractService;
import com.epms.Service.PartyinfoService;

@Controller
@RequestMapping(value="ContractController")
public class ContractController 
{
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private Contract contract;
	
	//��ѯ��ͬ��Ϣ
	@RequestMapping(value="/selectContract",produces="application/json;charset=utf-8")
	public @ResponseBody String selectContract(String startDate,String contractType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//�����������ݿ����ѯ�����ŵ�eilist�ļ�����
		
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Contract> contractList=contractService.selectContractById(startDate, contractType, before, limit, jobId);
		
		int count=contractService.count(startDate, contractType, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(contractList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//���ݺ�ͬ��ʼʱ���ѯ��ͬ��Ϣ
	@RequestMapping(value="selectContractByDate")
	public ModelAndView selectContractByDate(Contract contract,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		contract.setJobId(jobId);
		
		List<Contract> contractList=contractService.selectContractByDate(contract);
		if(contractList.isEmpty())
		{
			mv.addObject("selectContractMess", "���������ͬ��Ϣ��");
			mv.setViewName("contractList");
		}
		else
		{
			mv.addObject("contractList", contractList);
			mv.setViewName("contractList");
		}
		return mv;
	}
	
}
