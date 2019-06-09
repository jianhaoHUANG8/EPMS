package com.hjh.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Communication;
import com.hjh.Mapper.CommunicationMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.CommunicationService;

@Service("communicationService")
public class CommunicationServiceImpl implements CommunicationService
{
	@Autowired
	private CommunicationMapper communicationMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	//��ѯȫ��ͨѶ¼
	public List<Communication> selectAllCommunication(String departmentId,String jobId,String name,int before, int after)
	{
		return communicationMapper.selectAll(departmentId, jobId, name, before, after);
	}
	
	@Override
	public int count(String departmentId,String jobId,String name) {
		return communicationMapper.count(departmentId, jobId, name);
	}
	
	
	
	//�޸ĸ���ͨѶ��Ϣ
	@Override
	public String updateCommunication(Communication communication) 
	{
		JSONObject result = new JSONObject();
		if(communication.getEmail()==""||communication.getOfficeAddress()==""||communication.getOfficePhone().length()!=7)
		{
			return null;
		}
		else
		{
			if (communicationMapper.updateCommunication(communication)>0)
			{
				result.put("status", true);
				result.put("message", "�޸ĳɹ���");
			}
			else
			{
				result.put("status", false);
				result.put("message", "�޸�ʧ�ܣ�");
			}
		}
		System.out.println(result);
		return result.toString();
	}
	
	//���ݹ��Ų�ѯͨѶ��Ϣ
	@Override
	public Communication selectByJobId(int jobId) 
	{
		return communicationMapper.selectByJobId(jobId);
	}
	
	
}
