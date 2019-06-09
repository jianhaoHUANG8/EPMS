package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.FeedBack;
import com.hjh.Bean.Personalinfo;
import com.hjh.Mapper.FeedBackMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.FeedBackService;


@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService
{
	@Autowired
	private FeedBackMapper feedBackMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//�ύ������
	@Override
	public String insertFeedbackInfo(FeedBack feedback) 
	{	
		JSONObject result = new JSONObject();
		if(feedback.getReason().length()<=0)
		{
			return null;
		}
		else
		{
			if(feedback.getFeedbackType().equals(""))
			{
				result.put("status", false);
				result.put("message", "�ύʧ�ܣ�δѡ�������ͣ�");
			}
			else
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				feedback.setSubmitDate(dateFormat.format(new Date()));
				feedback.setState("�����");
				if(feedBackMapper.checkIfRepeat(feedback)>0)
				{
					result.put("status", false);
					result.put("message", "�ύʧ�ܣ������ظ��ύ��");
				}
				else
				{
					feedBackMapper.insertFeedbackInfo(feedback);
					result.put("status", true);
					result.put("message", "�ύ�ɹ���");
				}
			}
		}
		return result.toString();
	}

	//���ݲ���id��ѯ������Ϣ
	@Override
	public List<FeedBack> selectAllFeedback(String state,String department_id,String feedback_type,
			                                int before,int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			int departmentId=personalinfo.getDepartment().getDepartmentId();
			return feedBackMapper.selectFeedbackByDepartmentId(state, department_id, feedback_type, before, after, departmentId, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return feedBackMapper.selectFeedbackOnlyManager(state, department_id, feedback_type, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return feedBackMapper.selectFeedbackOnlyTotalManager(state, department_id, feedback_type, before, after);
		}
		return null;
	}
	
	//��ҳ��ѯʱ���������
	@Override
	public int count(String state,String department_id,String feedback_type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			int departmentId=personalinfo.getDepartment().getDepartmentId();
			return feedBackMapper.countByDepartmentId(state, department_id, feedback_type, departmentId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return feedBackMapper.countOnlyManager(state, department_id, feedback_type);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return feedBackMapper.countOnlyTotalManager(state, department_id, feedback_type);
		}
		return 0;
	}

	//����������Ϣ��
	@Override
	public String updateFeedback(FeedBack feedback) 
	{
		//�ȼ���Ƿ��Ѿ�������������������,���޷���������
		if(feedBackMapper.selectFeedbackById(feedback.getId()).getApprovalDate()==null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			feedback.setApprovalDate(dateFormat.format(new Date()));
			feedBackMapper.updateFeedback(feedback);
			return "true";
		}
		else
		{
			return "false";
		}
	}
	
	//���ݹ��Ų�ѯȫ��������Ϣ
	@Override
	public List<FeedBack> selectAllFeedBackByJobId(String state,String feedbackType,int before, int after,int jobId) 
	{
		return feedBackMapper.selectAllFeedBackByJobId(state, feedbackType, before, after, jobId);
	}

	@Override
	public int countByJobId(String state,String feedbackType,int jobId) 
	{
		return feedBackMapper.countByJobId(state, feedbackType, jobId);
	}
}
