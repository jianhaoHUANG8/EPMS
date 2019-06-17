package com.epms.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Personalinfo;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.PersonalinfoService;
import com.epms.Utils.CalculateAgeByBirthday;


@Service("personalinfoService")
public class PersonalinfoServiceImpl implements PersonalinfoService
{
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	//���ݹ��Ų�ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	@Override
	public Personalinfo selectPersonalByIdNotEducation(int jobId) {
		return personalinfoMapper.selectPersonalByIdNotEducation(jobId);
	}
	
	//���ݹ��Ų�ѯ������Ϣ
	public Personalinfo selectPersonalById(int jobId)
	{
		return personalinfoMapper.selectPersonalById(jobId);
	}
	
	//����������ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	@Override
	public Personalinfo selectPersonalByNameNotEducation(String name) {
		return personalinfoMapper.selectPersonalByNameNotEducation(name);
	}

	//����������ѯ������Ϣ
	@Override
	public Personalinfo selectPersonalByName(String name) {
		return personalinfoMapper.selectPersonalByName(name);
	}

	//����id��������ѯ������Ϣ
	@Override
	public List<Personalinfo> selectPersonByIdOrName(Personalinfo personal) 
	{
		return personalinfoMapper.selectPersonByIdOrName(personal);
	}

	//���ݲ���id��ѯ
	@Override
	public List<Personalinfo> selectPersonalByDepartmentId(Personalinfo personal) 
	{
		return personalinfoMapper.selectPersonalByDepartmentId(personal);
	}
	
	//��ѯȫ����Ա��Ϣ
	@Override
	public List<Personalinfo> selectAllPersonal(String occupation_id,String job_id,String name,
			String department_id,int before,int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���")||personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return personalinfoMapper.selectAllPersonal(occupation_id, job_id, name, department_id, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
			return personalinfoMapper.selectAllPersonalToManager(occupation_id, job_id, name, department_id, before, after, managerDepartmentId);
		}
		return null;
	}

	@Override
	public int count(String occupation_id,String job_id,String name,String department_id,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���")||personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return personalinfoMapper.count(occupation_id, job_id, name, department_id);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
			return personalinfoMapper.countToManager(occupation_id, job_id, name, department_id, managerDepartmentId);
		}
		return 0;
	}
	
	//�޸ĸ�����Ϣ
	@Override
	public String updatePersonal(Personalinfo personal)
	{
		JSONObject result = new JSONObject();
		CalculateAgeByBirthday calculate=new CalculateAgeByBirthday();
		try 
		{
			personal.setAge(calculate.getAge(personal.getBirthday()));
			if(personal.getAddress()==""||personal.getCensus()==""
					||personal.getBirthday()=="" ||personal.getEducation().getEducation()==""
					||personal.getEducation().getInDate()==""||personal.getEducation().getInDate()==""
					||personal.getEducation().getOutDate()==""||personal.getEducation().getSchoolName()==""
					||personal.getEmail()==""||personal.getMarital()==""||personal.getName()==""
					||personal.getPhone()==""||personal.getIdentityCard().length()!=18)
			{
				return null;
			}
			else if(personal.getAge()<=0)
			{
				result.put("status", false);
				result.put("message", "�޸�ʧ�ܣ����ձ�����һ����ȥ�����ڣ�");
				return result.toString();
			}
			else
			{
				if(personal.getSex().equals("��")||personal.getSex().equals("Ů"))
				{
					personalinfoMapper.updatePersonal(personal);
					result.put("status", true);
					result.put("message", "�޸ĳɹ���");
					return result.toString();
				}
				else
				{
					return null;
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
