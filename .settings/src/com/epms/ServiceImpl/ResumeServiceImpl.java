package com.epms.ServiceImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.ExternalResume;
import com.epms.Bean.InteralResume;
import com.epms.Bean.Personalinfo;
import com.epms.Bean.Resume;
import com.epms.Mapper.ExternalResumeMapper;
import com.epms.Mapper.InteralResumeMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.InteralResumeService;
import com.epms.Service.ResumeService;
import com.epms.Tools.CalculateDaySum;

@Service(value= "resumeService")
public class ResumeServiceImpl implements ResumeService
{
	@Autowired
	private Personalinfo personalinfo;
	
	@Autowired
	private Resume resume;
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	@Autowired
	private InteralResumeService interalResumeService;
	
	@Autowired
	private ExternalResumeService externalResumeService;

	@Override
	public List<Object> selectAllResumeByJobId(String departmentId,String occupationId,String status,int before, int after, int jobId) 
	{
		List<ExternalResume> externalResumelist = externalResumeService.selectAllExternalResume(departmentId,occupationId,status,before, after, jobId);
		List<InteralResume> interalResumeList = interalResumeService.selectAllInteralResume(departmentId,occupationId,status,before, after, jobId);

		List list = new ArrayList();
		Iterator it1 = interalResumeList.iterator();
		while (it1.hasNext()) {
			list.add(it1.next());
		}
		Iterator it2 = externalResumelist.iterator();
		while (it2.hasNext()) {
			list.add(it2.next());
		}
		return list;
		
	}

	@Override
	public int countByJobId(String departmentId,String occupationId,String status,int jobId) 
	{
		int count = externalResumeService.countSelectAllExternalResume(departmentId,occupationId,status,jobId)
				+ interalResumeService.countAllInteralResume(departmentId,occupationId,status,jobId);
		return count;
	}

	@Override
	public String updateAllResume(Resume resume,String interviewName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		resume.setApprovalDate(dateFormat.format(new Date()));
		if(!resumeMapper.selectResumeById(resume.getId()).getStatus().equals("�����"))
		{
			result.put("status", false);
			result.put("message", "���ʧ�ܣ������ظ���ˣ�");
		}
		else{
			if(resume.getStatus().equals("ͨ��"))
			{
				if(interviewName.length()<=0)
				{
					result.put("status", false);
					result.put("message", "���ʧ�ܣ�δ��д������Ա������");
				}
				else if(resume.getInterviewDate().length()<=0)
				{
					result.put("status", false);
					result.put("message", "���ʧ�ܣ�δ��д����ʱ�䣡");
				}
				else if(checkIfFuture(resume)<=0) //�ж�����Ŀ�ʼʱ��ͽ���ʱ���Ƿ����
				{
					result.put("status", false);
					result.put("message", "���ʧ�ܣ�����ʱ�������δ�����ڣ�");
				}
				else if(personalinfoMapper.selectPersonalByNameNotEducation(interviewName)==null)
				{
					result.put("status", false);
					result.put("message", "���ʧ�ܣ���˾���޴��ˣ�");
				}
				else 
				{
					Resume resume2=resumeMapper.selectResumeById(resume.getId());
					personalinfo=personalinfoMapper.selectPersonalByNameNotEducation(interviewName);
					if(resume2.getToDepartment().getDepartmentId()!=personalinfo.getDepartment().getDepartmentId())
					{
						result.put("status", false);
						result.put("message", "���ʧ�ܣ�����д����������Ȩ�����Դ����룡");
					}
					else
					{
						resume.setInterviewId(personalinfo.getJobId());
						resumeMapper.updateAllResume(resume);
						result.put("status", true);
						result.put("message", "��˳ɹ���");
					}
				}
			}
			else
			{
				resumeMapper.updateResumeNotAll(resume);
				result.put("status", true);
				result.put("message", "��˳ɹ���");
			}
		}
		return result.toString();
	}
	
	//�ж�����ʱ���Ƿ�Ϊδ������
	public int checkIfFuture(Resume resume) 
	{
		// �����������(�������а����ķ����ڼ�������)
		CalculateDaySum cds = new CalculateDaySum();
		int count =cds.calculate(resume.getApprovalDate(),resume.getInterviewDate());
		return count;
	}

}
