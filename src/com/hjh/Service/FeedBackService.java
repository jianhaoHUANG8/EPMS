package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.FeedBack;

public interface FeedBackService 
{
	//���뷴����Ϣ
	public String insertFeedbackInfo(FeedBack feedback);
	
	//�ϼ���ѯ�¼��ύ�ķ�����Ϣ
	public List<FeedBack> selectAllFeedback(String state,String department_id,String feedback_type,
			                                int before,int after,int jobId);
	public int count(String state,String department_id,String feedback_type,int jobId);
	
	//����������Ϣ
	String updateFeedback(FeedBack feedback);
	
	//�Լ���ѯ�ύ���ϼ���ȫ��������Ϣ
	public List<FeedBack> selectAllFeedBackByJobId(String state,String feedbackType,
			                                       int before,int after,int jobId);
	public int countByJobId(String state,String feedbackType,int jobId);
}
