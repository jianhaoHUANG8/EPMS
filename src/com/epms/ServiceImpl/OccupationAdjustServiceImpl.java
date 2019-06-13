package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Apply;
import com.epms.Bean.Applytype;
import com.epms.Bean.OccupationAdjust;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.ApplyMapper;
import com.epms.Mapper.OccupationAdjustMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.OccupationAdjustService;

@Service("occupationAdjustService")
public class OccupationAdjustServiceImpl implements OccupationAdjustService
{
	@Autowired
	private Apply apply;
	
	@Autowired
	private Applytype applytype;
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private OccupationAdjustMapper occupationAdjustMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	//�ύн�ʵ��������
	@Override
	public String insertOccupationAdjust(OccupationAdjust occupationAdjust,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		occupationAdjust.getApply().setSubmitDate(dateFormat.format(new Date()));
		occupationAdjust.getApply().setState("�����");
		
		if(!checkErrorMess(occupationAdjust,passiveName).equals(""))//����Ƿ���ִ���������Ϣ
		{
			return checkErrorMess(occupationAdjust,passiveName);
		}
		else
		{
			if(checkIdentity(occupationAdjust.getApply()))//������trueʱ��˵���ǲ��ž�����Ҫ����checkIfValidInsert��������Ƿ��ǲ��ž������Ĳ���ְԱ
			{
				if(checkIfValidInsert(occupationAdjust,passiveName))//������true��˵���ڸ���Χ�ڣ��ɽ����ύ
				{
					detailinsertSalaryAdjust(occupationAdjust,passiveName);
					result.put("status", true);
					result.put("message", "�ύ�ɹ���");
				} 
				else //������false��˵�����ڸ���Χ�ڣ����ɽ����ύ
				{ 
					result.put("status", false);
				    result.put("message", "�ύʧ�ܣ���д����������Ȩ��Ϊ���ύ�����");
				}
			}
			else //������false��˵�����ܾ���ֱ�ӵ���mapperʵ�ֹ��ܼ���
			{
				detailinsertSalaryAdjust(occupationAdjust,passiveName);
				result.put("status", true);
				result.put("message", "�ύ�ɹ���");
			}
		}
		return result.toString();
	}
	
	public String checkErrorMess(OccupationAdjust occupationAdjust,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(occupationAdjust.getApply().getReason().length()<=0||passiveName.length()<=0)//����������Ϣ�Ƿ�����
		{
			return null;
		}
		else if(personalinfoMapper.selectPersonalByNameNotEducation(passiveName)==null)//�Ƿ������˴���Ա������
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ����޴�Ա����");
			return result.toString();
		}
		else //�Ƿ�Ϊ�Լ��ύ�������
		{
			Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
			occupationAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
			if((occupationAdjust.getApply().getWriteId()==occupationAdjust.getApply().getPassiveId()))
			{
				result.put("status", false);
				result.put("message", "�ύʧ�ܣ�����Ϊ�Լ���д�����");
				return result.toString();
			}
			else if(occupationAdjust.getToDepartmentId()==personalinfoPassiver.getDepartment().getDepartmentId()
					&&occupationAdjust.getToOccupationId()==personalinfoPassiver.getOccupation().getOccupationId())
			{
				result.put("status", false);
				result.put("message", "�ύʧ�ܣ������ĸ�λ���ޱ䶯��");
				return result.toString();
			}
			else
			{ 
				if(occupationAdjustMapper.selectIfRepeat(occupationAdjust)>0)//�Ƿ��ظ��ύ
				{
					result.put("status", false);
					result.put("message", "�ύʧ�ܣ������ظ��ύ��");
					return result.toString();
				}
				else
				{
					return ""; 
				}
			}
		}
	}
	
	public void detailinsertSalaryAdjust(OccupationAdjust occupationAdjust,String passiveName)
	{
		//�Ȳ���apply��ע���ʱ����������������ͣ�������
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		occupationAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
		occupationAdjust.getApply().setApplytype(applytype);
		occupationAdjust.getApply().getApplytype().setApplytypeId(5);
		applyMapper.insertApply(occupationAdjust.getApply());
			
		//�ٲ���occupationAdjust��
		occupationAdjust.setApplyId(applyMapper.selectApplyMaxId());
		occupationAdjust.setPreDepartmentId(personalinfoPassiver.getDepartment().getDepartmentId());
		occupationAdjust.setPreOccupationId(personalinfoPassiver.getOccupation().getOccupationId());
		occupationAdjust.setType(checkType(occupationAdjust));
		
		if(occupationAdjustMapper.insertOccupationAdjust(occupationAdjust)<=0)//��ְ�������Ϣ����ʧ��ʱ��Ӧ���ո�apply����ļ�¼ɾ��
		{
			applyMapper.delectApplyMaxId();
		}
	}
	
	//����д���������������ǲ��ž���ʱ��Ҫ�����д�Ĺ����Ƿ�������������Ĳ��ŵ�Ա��
	public boolean checkIfValidInsert(OccupationAdjust occupationAdjust,String passiveName)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(occupationAdjust.getApply().getWriteId());
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		if(personalinfoWriter!=null && personalinfoPassiver!=null)//�ȼ���Ƿ���ڴ˹�����Ա
		{
			if(personalinfoWriter.getDepartment().getDepartmentId()==personalinfoPassiver.getDepartment().getDepartmentId())
			{
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//���������ǲ��ž������ܾ��������˵�Ȩ�޲�ͬ��
	public boolean checkIdentity(Apply apply)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(apply.getWriteId());
		if(personalinfoWriter.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return false;
		} else{
			return true;
		}
	}
	
	public String checkType(OccupationAdjust occupationAdjust)
	{
		if(occupationAdjust.getToOccupationId()<occupationAdjust.getPreOccupationId())
		{
			return "��ְ";
		}
		else if(occupationAdjust.getToOccupationId()==occupationAdjust.getPreOccupationId())
		{
			return "ƽ��";
		}
		else if(occupationAdjust.getToOccupationId()>occupationAdjust.getPreOccupationId())
		{
			return "��ְ";
		}
		return "";
	}
	
	//���������id��ѯ���ύ��ȫ��������Ϣ
	@Override
	public List<OccupationAdjust> selectAllOccupationAdjustByWriteId(String pre_departmentid,
		   String state,String type,int before, int after, int jobId) 
	{
		return  occupationAdjustMapper.selectAllOccupationAdjustByWriteId(pre_departmentid, state, type, before, after, jobId);
	}

	@Override
	public int countByWriteId(String pre_departmentid,String state,String type,int jobId) 
	{
		return occupationAdjustMapper.countByWriteId(pre_departmentid, state, type, jobId);
	}
	
	//�ϼ��鿴�����ύ��ȫ��ְλ��������
	@Override
	public List<OccupationAdjust> selectAllSalaryAdjust(String pre_departmentid, String state, String type, int before,
			int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return occupationAdjustMapper.selectOccupationAdjustToLeader(pre_departmentid, state, type, before, after, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return occupationAdjustMapper.selectOccupationAdjustToLeader(pre_departmentid, state, type, before, after, 3);
		}
		return null;
	}
	
	@Override
	public int countAllOccupationAdjust(String pre_departmentid, String state,String type, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return occupationAdjustMapper.countToLeader(pre_departmentid, state, type, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return occupationAdjustMapper.countToLeader(pre_departmentid, state, type, 3);
		}
		return 0;
	}
	
	//����ְλ��������
	@Override
	public String updateOccupationAdjust(OccupationAdjust occupationAdjust,String state) 
	{
		//ͨ��id��ô����������Ϣ
		apply=applyMapper.selectApplyById(occupationAdjust.getApplyId());
		occupationAdjust.setApply(apply);
		// �ȼ���Ƿ��Ѿ�������������������,���޷���������
		if (apply.getApprovalDate() == null) 
		{
			//�ж��Ƿ�ͬ�⣬��ͬ�����޸ĵ������
			if(state.equals("ͬ��"))
			{
				occupationAdjust=occupationAdjustMapper.selectOccupationAdjustByApplyId(occupationAdjust.getApplyId());
				personalinfoMapper.updatePersonalByOccupationAdjust(
						 occupationAdjust.getToOccupationId(), occupationAdjust.getToDepartmentId(),apply.getPassiveId());
			}
			// ���������״̬������ʱ��
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			apply.setId(occupationAdjust.getApplyId());
			apply.setState(state);
			applyMapper.updateApply(apply);
			return "true";
		} 
		else {
			return "false";
		}
	}

	//���ݹ��Ų�ѯ��ص�ְλ������¼
	@Override
	public List<OccupationAdjust> selectAllOccupationAdjustByJobId(String state, String type, int before, int after, int jobId) 
	{
		return occupationAdjustMapper.selectAllOccupationAdjustByJobId(state, type, before, after, jobId);
	}

	@Override
	public int countByJobId(String state, String type, int jobId) 
	{
		return occupationAdjustMapper.countByJobId(state, type, jobId);
	}
	
}
