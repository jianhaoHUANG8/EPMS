package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Apply;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.ApplyMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.UserMapper;
import com.epms.Service.ApplyService;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService
{
	@Autowired
	private ApplyMapper applyMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//�ύ����������Ϣ
	@Override
	public String insertApply(Apply apply,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		apply.setSubmitDate(dateFormat.format(new Date()));
		apply.setState("�����");
		if(apply.getReason().length()<=0||passiveName=="")//����������Ϣ�Ƿ�����
		{
			return null;
		} 
		else
		{
			if(checkIdentity(apply))//������trueʱ��˵���ǲ��ž�����Ҫ����checkIfValidInsert��������Ƿ��ǲ��ž������Ĳ���ְԱ
			{
				if(checkIfValidInsert(apply,passiveName))//������true��˵���ڸ���Χ�ڣ��ɽ����ύ
				{
					Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
					apply.setPassiveId(personalinfoPassiver.getJobId());
					return detailInsertApply(apply,passiveName);
				} else //������false��˵�����ڸ���Χ�ڣ����ɽ����ύ
					{
					result.put("status", false);
					result.put("message", "�ύʧ�ܣ���д����������Ȩ��Ϊ���ύ�����");
					}
			}
			else //������false��˵�����ܾ���ֱ�ӵ���mapperʵ�ֹ��ܼ���
			{
				if(personalinfoMapper.selectPersonalByNameNotEducation(passiveName)!=null)
				{
					Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
					apply.setPassiveId(personalinfoPassiver.getJobId());
					return detailInsertApply(apply,passiveName);
				} else{
					result.put("status", false);
					result.put("message", "�ύʧ�ܣ����޴�Ա����");
				}
			}
		}
		return result.toString();
	}
	
	//����д���������������ǲ��ž���ʱ��Ҫ�����д�Ĺ����Ƿ�������������Ĳ��ŵ�Ա��
	public boolean checkIfValidInsert(Apply apply,String passiveName)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(apply.getWriteId());
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
	
	//�鿴�Ƿ��ظ��ύ
	public boolean checkIfRepeat(Apply apply)
	{
		if(applyMapper.checkIfRepeat(apply)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String detailInsertApply(Apply apply,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(checkIfRepeat(apply))
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ������ظ��ύ��");
		}
		else if(apply.getPassiveId()==apply.getWriteId())
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ�����Ϊ�Լ��������");
		}
		else
		{
			Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
			if(apply.getApplytype().getApplytypeId()==2)
			{
				if(personalinfoPassiver.getOccupation().getOccupationId()!=0)
				{
					result.put("status", false);
					result.put("message", "�ύʧ�ܣ��������˲���ʵϰ��������Ϊ������ʵϰ��ת�����");
				}
				else
				{
					applyMapper.insertApply(apply);
					result.put("status", true);
					result.put("message", "�ύ�ɹ���");
				}
			}
			else
			{
				applyMapper.insertApply(apply);
				result.put("status", true);
				result.put("message", "�ύ�ɹ���");
			}
		}
		return result.toString();
	}
	//��ѯȫ��ֱ�������ύ������������Ϣ
	@Override
	public List<Apply> selectAllApply(String department_id,String state,String applytype_name,
			                          int before, int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return applyMapper.selectApplyToTotalManager(department_id, state, applytype_name, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return applyMapper.selectApplyToBoard(department_id, state, applytype_name, before, after);
		}
		return null;
	}

	@Override
	public int count(String department_id,String state,String applytype_name,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return applyMapper.countToTotalManager(department_id, state, applytype_name);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return applyMapper.countToBoard(department_id, state, applytype_name);
		}
		return 0;
	}
	
	//������������
	@Override
	public String updateApply(Apply apply) 
	{
		String state=apply.getState();
		apply=applyMapper.selectApplyById(apply.getId());
		apply.setState(state);
		//�ȼ���Ƿ��Ѿ�������������������,���޷���������
		if(apply.getApprovalDate()==null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			if(apply.getApplytype().getApplytypeName().equals("����Ա��") && apply.getState().equals("ͬ��"))
			{
				if(userMapper.updateState(apply.getPassiveId(), "��ְ")>0)
				{
					applyMapper.updateApply(apply);
					return "true";
				}
				else
				{
					return "false";
				}
			}
			else if(apply.getApplytype().getApplytypeName().equals("ʵϰ��ת��") && apply.getState().equals("ͬ��"))
			{
				if(personalinfoMapper.updatePersonalToEmployee(apply.getPassiveId())>0)
				{
					applyMapper.updateApply(apply);
					return "true";
				}
				else
				{
					return "false";
				}
			}
			else
			{
				applyMapper.updateApply(apply);
				return "true";
			}
		}
		else
		{
			return "false";
		}
	}

	//��ѯ�ύ��ֱ���ϼ�������������Ϣ
	@Override
	public List<Apply> selectAllApplyByWriteId(String department_id,String state,String applytype_name,int before, int after, int jobId) 
	{
		return applyMapper.selectAllApplyByWriteId(department_id, state, applytype_name, before, after, jobId);
	}
	
	@Override
	public int countByWriteId(String department_id,String state,String applytype_name,int jobId) 
	{
		return applyMapper.countByWriteId(department_id, state, applytype_name, jobId);
	}
	
	
}
