package com.hjh.Service;

import java.util.List;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.RewardPunish;

public interface RewardPunishService {
	//��ȡ���˵Ľ��ͼ�¼
	public List<RewardPunish> showRewardPunish(int jobId,int before, int after);
	//��ȡ���˵Ľ��ͼ�¼����
	public int countshowRewardPunish(int jobId);
	//��ѯ���š������Ƿ���ȷ���ڸ������˲�����
	public Personalinfo selectUserById(int jobId, String name, int applyId);
	//���Ա���Ľ���
	public int addStaffRewardPunish(int jobId, String type, String content,String reason, String setDate, int applyId);
	//��ѯ��Ҫ��˵�����Ա������
	public List<RewardPunish> showVerifyRewardPunish(int before, int after);
	//��ȡ��Ҫ��˵�����Ա�����͵�����
	public int countVerifyRewardPunish();
	//�������Ա������ͨ��
	public int verifyRewardPunishYes(int jobId, String reason, String setDate,int approveId, String approveDate);
	//�������Ա������δͨ��
	public int verifyRewardPunishNo(int jobId, String reason, String setDate,int approveId, String approveDate);
	//��ѯ����Ա�����͵ļ�¼
	public List<RewardPunish> showApplyRewardPunishRecord(int jobId,int before, int after);
	//��ѯ����Ա�����͵ļ�¼ ����
	public int countApplyRewardPunishRecord(int jobId);
	//��ѯ���Ա�����͵ļ�¼
	public List<RewardPunish> showVerifyRewardPunishRecord(int jobId,int before, int after);
	//��ѯ���Ա�����͵ļ�¼����
	public int countVerifyRewardPunishRecord(int jobId);

}
