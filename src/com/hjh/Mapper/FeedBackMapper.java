package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.FeedBack;

@Repository
public interface FeedBackMapper 
{
   //���뷴����Ϣ
   int insertFeedbackInfo(FeedBack feedback);
   
   //���ݲ��Ų�ѯ������Ϣ
   List<FeedBack> selectFeedbackByDepartmentId(@Param("state")String state,@Param("department_id")String department_id,
		                                       @Param("feedback_type")String feedback_type,@Param("before") int before,
		                                       @Param("after") int after,@Param("departmentId")int departmentId,
		                                       @Param("jobId")int jobId);
   int countByDepartmentId(@Param("state")String state,@Param("department_id")String department_id,
                           @Param("feedback_type")String feedback_type,@Param("departmentId")int departmentId);
   
   //��ѯȫ�����ž���ķ�����Ϣ
   List<FeedBack> selectFeedbackOnlyManager(@Param("state")String state,@Param("department_id")String department_id,
                                            @Param("feedback_type")String feedback_type,@Param("before") int before,
                                            @Param("after") int after);
   int countOnlyManager(@Param("state")String state,@Param("department_id")String department_id,
                        @Param("feedback_type")String feedback_type);
   
   //��ѯȫ���ܾ���ķ�����Ϣ
   List<FeedBack> selectFeedbackOnlyTotalManager(@Param("state")String state,@Param("department_id")String department_id,
                                                 @Param("feedback_type")String feedback_type,@Param("before") int before,
                                                 @Param("after") int after);
   int countOnlyTotalManager(@Param("state")String state,@Param("department_id")String department_id,
                             @Param("feedback_type")String feedback_type);
   
   //����������Ϣ
   int updateFeedback(FeedBack feedback);
   
   //��������id��ѯ������Ϣ
   FeedBack selectFeedbackById(int id);
   
   //���ݹ��Ų�ѯȫ��������Ϣ
   List<FeedBack> selectAllFeedBackByJobId(@Param("state")String state,@Param("feedbackType")String feedbackType,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
   int countByJobId(@Param("state")String state,@Param("feedbackType")String feedbackType,@Param("jobId")int jobId);
   
   //��ѯ�Ƿ��ظ��ύ
   int checkIfRepeat(FeedBack feedback);
   
}
