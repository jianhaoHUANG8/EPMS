package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Apply;
import com.hjh.Bean.Applytype;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.SalaryAdjust;
import com.hjh.Bean.StaffWage;
import com.hjh.Mapper.ApplyMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Mapper.SalaryAdjustMapper;
import com.hjh.Mapper.StaffWageMapper;
import com.hjh.Service.SalaryAdjustService;

@Service("salaryAdjustService")
public class SalaryAdjustServiceImpl implements SalaryAdjustService
{
	@Autowired
	private Apply apply;
	
	@Autowired
	private Applytype applytype;
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private SalaryAdjustMapper salaryAdjustMapper;
	
	@Autowired
	private StaffWageMapper staffWageMapper;
	
	@Autowired
	private StaffWage staffWage;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//�ύн�ʵ��������
	@Override
	public String insertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		salaryAdjust.getApply().setSubmitDate(dateFormat.format(new Date()));
		salaryAdjust.getApply().setState("�����");
		
		if(!checkErrorMess(salaryAdjust,passiveName).equals(""))//����Ƿ���ִ���������Ϣ
		{
			return checkErrorMess(salaryAdjust,passiveName);
		}
		else
		{
			if(checkIdentity(salaryAdjust.getApply()))//������trueʱ��˵���ǲ��ž�����Ҫ����checkIfValidInsert��������Ƿ��ǲ��ž������Ĳ���ְԱ
			{
				if(checkIfValidInsert(salaryAdjust.getApply(),passiveName))//������true��˵���ڸ���Χ�ڣ��ɽ����ύ
				{
					detailinsertSalaryAdjust(salaryAdjust,passiveName);
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
				detailinsertSalaryAdjust(salaryAdjust,passiveName);
				result.put("status", true);
				result.put("message", "�ύ�ɹ���");
			}
		}
		return result.toString();
	}
	
	public String checkErrorMess(SalaryAdjust salaryAdjust,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(salaryAdjust.getApply().getReason().length()<=0||passiveName.length()<=0
				||salaryAdjust.getSalaryadjustMoneny()<=0)//����������Ϣ�Ƿ�����
		{
			return null;
		}
		else if(salaryAdjust.getSalaryadjustType().length()<=0)
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ���δѡ��н�ʵ������ͣ�");
			return result.toString();
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
			salaryAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
			if((salaryAdjust.getApply().getWriteId()==salaryAdjust.getApply().getPassiveId()))
			{
				result.put("status", false);
				result.put("message", "�ύʧ�ܣ�����Ϊ�Լ���д�����");
				return result.toString();
			}
			else
			{ 
				if(salaryAdjustMapper.selectIfRepeat(salaryAdjust)>0)//�Ƿ��ظ��ύ
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
	
	public void detailinsertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName)
	{
		//�Ȳ���apply��ע���ʱ����������������ͣ�������
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		salaryAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
		salaryAdjust.getApply().setApplytype(applytype);
		salaryAdjust.getApply().getApplytype().setApplytypeId(4);;
		applyMapper.insertApply(salaryAdjust.getApply());
			
		//�ٲ���salaryadjust��
		salaryAdjust.setApplyId(applyMapper.selectApplyMaxId());
		salaryAdjustMapper.insertSalaryAdjust(salaryAdjust);
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
	
	//��������˹��Ų�ѯн�ʵ���������Ϣ
	@Override
	public List<SalaryAdjust> selectAllSalaryAdjustByWriteId(String department_id,String state,String salaryadjust_type,int before,int after, int jobId) 
	{
		return salaryAdjustMapper.selectAllSalaryAdjustByWriteId(department_id, state, salaryadjust_type, before, after, jobId);
	}

	@Override
	public int countByWriteId(String department_id,String state,String salaryadjust_type,int jobId) 
	{
		return salaryAdjustMapper.countByWriteId(department_id, state, salaryadjust_type, jobId);
	}
	
	//�ϼ��鿴�����ύ��ȫ��н�ʵ�������
	@Override
	public List<SalaryAdjust> selectAllSalaryAdjust(String department_id,String state,
			          String salaryadjust_type,int before, int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return salaryAdjustMapper.selectSalaryAdjustToLeader(department_id, state, salaryadjust_type, before, after, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return salaryAdjustMapper.selectSalaryAdjustToLeader(department_id, state, salaryadjust_type, before, after, 3);
		}
		return null;
	}

	@Override
	public int countAllSalaryAdjust(String department_id,String state,String salaryadjust_type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return salaryAdjustMapper.countToLeader(department_id, state, salaryadjust_type, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return salaryAdjustMapper.countToLeader(department_id, state, salaryadjust_type, 3);
		}
		return 0;
	}

	//����н�ʵ�������
	@Override
	public String updateSalaryAdjust(SalaryAdjust salaryAdjust,String state) 
	{
		// �ȼ���Ƿ��Ѿ�������������������,���޷���������
		if (applyMapper.selectApplyById(salaryAdjust.getApplyId()).getApprovalDate() == null) 
		{
			if(state.equals("ͬ��"))
			{
				//��������Ա���Ĺ���
				staffWage=staffWageMapper.selectStaffWageByJobId(applyMapper.selectApplyById(salaryAdjust.getApplyId()).getPassiveId());
				//�ж��Ǽ�н���Ǽ�н
				if(salaryAdjust.getSalaryadjustType().equals("��н"))
				{
					staffWage.setWage(staffWage.getWage() + salaryAdjust.getSalaryadjustMoneny());
				}
				else
				{
					staffWage.setWage(staffWage.getWage() - salaryAdjust.getSalaryadjustMoneny());
				}
				staffWageMapper.updatetStaffWage(staffWage);
			}
			//���������״̬������ʱ��
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			apply.setId(salaryAdjust.getApplyId());
			apply.setState(state);
			applyMapper.updateApply(apply);
			return "true";
		} 
		else 
		{
			return "false";
		}
	}

	@Override
	public List<SalaryAdjust> selectAllSalaryAdjustByJobId(String state,
			int before, int after, int jobId) 
	{
		return salaryAdjustMapper.selectAllSalaryAdjustByJobId(state, before, after, jobId);
	}

	@Override
	public int countByJobId(String state, int jobId) 
	{
		return salaryAdjustMapper.countByJobId(state, jobId);
	}

}
