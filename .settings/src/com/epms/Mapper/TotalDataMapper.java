package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.SocialSecurity;
import com.epms.Bean.TotalData;

@Repository
public interface TotalDataMapper 
{
	//�������������Ϣ
	int insertTotalDataByTotalVacate(@Param("jobId") int jobId,@Param("totalVacate") int totalVacate);
	
	//�޸����������Ϣ
	int updateTotalDataByTotalVacate(@Param("jobId") int jobId,@Param("totalVacate") int totalVacate);

	//���ݹ��Ų�ѯ���µ�����������ٵ����������˴������Ӱ�ʱ��(�԰�СʱΪ��λ)
	TotalData selectByJobId(@Param("jobId") int jobId);
	
	//��ѯȫ����Ա�ĵ��µ�����������ٵ����������˴������Ӱ�ʱ��(�԰�СʱΪ��λ)
	List<TotalData> selectAllDataSum();
	
	//ɾ��������
	int deleteAll();
	
	
	//�������ˡ��ٵ����Ӱ�(�����ռӰࡢ�ڼ��ռӰ�)��ȱ����Ϣ
	int insertTotalDataByLately(@Param("jobId") int jobId,@Param("totalLately") int totalLately);
	int insertTotalDataByEarlyLeave(@Param("jobId") int jobId,@Param("totalEarlyleave") int totalEarlyleave);
	int insertTotalDataByWorkOverTime(@Param("jobId") int jobId,@Param("totalWorkOverTime") int totalWorkOverTime);
	int insertTotalDataByNotWorkOverTime(@Param("jobId") int jobId,@Param("totalNotWorkOverTime") int totalNotWorkOverTime);
	int insertTotalDataByAbsence(@Param("jobId") int jobId,@Param("totalAbsence") int totalAbsence);	
	
	//�޸����ˡ��ٵ����Ӱ�(�����ռӰࡢ�ڼ��ռӰ�)��ȱ�ڡ���ѵ��Ϣ 
	int updateTotalDataByLately(@Param("jobId") int jobId,@Param("totalLately") int totalLately);
	int updateTotalDataByEarlyLeave(@Param("jobId") int jobId,@Param("totalEarlyleave") int totalEarlyleave);
	int updateTotalDataByWorkOverTime(@Param("jobId") int jobId,@Param("totalWorkOverTime") int totalWorkOverTime);
	int updateTotalDataByNotWorkOverTime(@Param("jobId") int jobId,@Param("totalNotWorkOverTime") int totalNotWorkOverTime);
	int updateTotalDataByAbsence(@Param("jobId") int jobId,@Param("totalAbsence") int totalAbsence);
	int updateTotalDataByCaltivateday(@Param("jobId") int jobId,@Param("totalCaltivateDay") int totalCaltivateDay);
	
	//��ҳ��ѯ��������
	public List<TotalData> selectDataSum (@Param("before") int before,@Param("limit") int limit);
	public int countDataSum ();
	
	
}
