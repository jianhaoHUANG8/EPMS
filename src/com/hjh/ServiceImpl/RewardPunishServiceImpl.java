package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.RewardPunish;
import com.hjh.Mapper.RewardPunishMapper;
import com.hjh.Service.RewardPunishService;
@Service("rewardPunishService")
public class RewardPunishServiceImpl implements RewardPunishService{
	@Autowired
	private RewardPunishMapper rewardPunishMapper;
	
	//��ȡ���˵Ľ��ͼ�¼
	public List<RewardPunish> showRewardPunish(int jobId, int before, int after) {
		return rewardPunishMapper.showRewardPunish(jobId,before,after);
	}
	
	//��ȡ���˵Ľ��ͼ�¼����
	public int countshowRewardPunish(int jobId) {
		return rewardPunishMapper.countshowRewardPunish(jobId);
	}

	//��ѯ���š������Ƿ���ȷ���ڸ������˲�����
	public Personalinfo selectUserById(int jobId, String name, int applyId) {
		return rewardPunishMapper.selectUserById(jobId,name,applyId);
	}

	//���Ա���Ľ���
	public int addStaffRewardPunish(int jobId, String type, String content,String reason, String setDate, int applyId) {
		return rewardPunishMapper.addStaffRewardPunish(jobId,type,content,reason,setDate,applyId);
	}

	//��ѯ��Ҫ��˵�����Ա������
	public List<RewardPunish> showVerifyRewardPunish(int before, int after) {
		return rewardPunishMapper.showVerifyRewardPunish(before,after);
	}

	//��ȡ��Ҫ��˵�����Ա�����͵�����
	public int countVerifyRewardPunish() {
		return rewardPunishMapper.countVerifyRewardPunish();
	}

	//�������Ա������ͨ��
	public int verifyRewardPunishYes(int jobId, String reason, String setDate,int approveId, String approveDate) {
		return rewardPunishMapper.verifyRewardPunishYes(jobId,reason,setDate,approveId,approveDate);
	}

	//�������Ա������δͨ��
	public int verifyRewardPunishNo(int jobId, String reason, String setDate,int approveId, String approveDate) {
		return rewardPunishMapper.verifyRewardPunishNo(jobId,reason,setDate,approveId,approveDate);
	}

	//��ѯ����Ա�����͵ļ�¼
	public List<RewardPunish> showApplyRewardPunishRecord(int jobId,int before, int after) {
		return rewardPunishMapper.showApplyRewardPunishRecord(jobId,before,after);
	}

	//��ѯ����Ա�����͵ļ�¼ ����
	public int countApplyRewardPunishRecord(int jobId) {
		return rewardPunishMapper.countApplyRewardPunishRecord(jobId);
	}

	//��ѯ���Ա�����͵ļ�¼
	public List<RewardPunish> showVerifyRewardPunishRecord(int jobId,int before, int after) {
		return rewardPunishMapper.showVerifyRewardPunishRecord(jobId,before,after);
	}

	//��ѯ���Ա�����͵ļ�¼����
	public int countVerifyRewardPunishRecord(int jobId) {
		return rewardPunishMapper.countVerifyRewardPunishRecord(jobId);
	}

	

	
}
