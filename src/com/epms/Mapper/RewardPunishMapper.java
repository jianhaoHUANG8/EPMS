package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



import com.epms.Bean.Personalinfo;
import com.epms.Bean.RewardPunish;

@Repository
public interface RewardPunishMapper {

	//��ȡ���˵Ľ��ͼ�¼
	List<RewardPunish> showRewardPunish(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//��ȡ���˵Ľ��ͼ�¼����
	int countshowRewardPunish(int jobId);
	
	//��ѯ���š������Ƿ���ȷ���ڸ������˲�����
	Personalinfo selectUserById(@Param("jobId")int jobId,@Param("name") String name,@Param("applyId") int applyId);
	
	//���Ա���Ľ���
	int addStaffRewardPunish(@Param("jobId")int jobId,@Param("type") String type,@Param("content") String content,@Param("reason")String reason,@Param("setDate") String setDate,@Param("applyId") int applyId);

	//��ѯ��Ҫ��˵�����Ա������
	List<RewardPunish> showVerifyRewardPunish(@Param("before")int before,@Param("after") int after);

	//��ȡ��Ҫ��˵�����Ա�����͵�����
	int countVerifyRewardPunish();

	//�������Ա������ͨ��
	int verifyRewardPunishYes(@Param("jobId")int jobId,@Param("reason") String reason,@Param("setDate") String setDate,@Param("approveId")int approveId,@Param("approveDate") String approveDate);

	//�������Ա������δͨ��
	int verifyRewardPunishNo(@Param("jobId")int jobId,@Param("reason") String reason,@Param("setDate") String setDate,@Param("approveId")int approveId,@Param("approveDate") String approveDate);

	//��ѯ����Ա�����͵ļ�¼
	List<RewardPunish> showApplyRewardPunishRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//��ѯ����Ա�����͵ļ�¼ ����
	int countApplyRewardPunishRecord(int jobId);

	//��ѯ���Ա�����͵ļ�¼
	List<RewardPunish> showVerifyRewardPunishRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//��ѯ���Ա�����͵ļ�¼����
	int countVerifyRewardPunishRecord(int jobId);

}
