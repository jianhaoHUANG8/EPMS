package com.epms.Mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.CultivateRecord;

//��Ƹ�ƻ�����
@Repository
public interface CultivateRecordMapper 
{
	//������ѵ
		int insertCultivateRecord(@Param("cultivateId") int cultivateId,@Param("participatorId") int participatorId,
				@Param("status") String status);
		
		//��ѯ�Ƿ��ظ�����
		int checkIfRepeat(@Param("cultivateId") int cultivateId,@Param("participatorId") int participatorId);
		
		//ɾ���������
		int deleteMaxId();
		
		//Ա����ѯ�Լ���������ѵ�γ�
		List<CultivateRecord> selectCultivateRecordByJobId(@Param("cultivateId") String cultivateId,@Param("status") String status,@Param("before") int before,@Param("after") int after,@Param("jobId") int jobId);
		int countSelectCultivateRecordByJobId(@Param("cultivateId") String cultivateId,@Param("status") String status,@Param("jobId") int jobId);
		
		//�������º͹��Ų�ѯ�Ƿ����б�����ѵ
		int countSumByDate(@Param("jobId") int jobId,@Param("year") String year,@Param("month") String month);
		
		//�ϼ���ѯֱ���¼����ѱ�����ѵ��¼
		List<CultivateRecord> selectCultivateRecordToManager(@Param("before") int before,@Param("after") int after,@Param("managerId") int managerId);
		int countToManager(@Param("managerId") int managerId);
		
		List<CultivateRecord> selectCultivateRecordToTotalManager(@Param("before") int before,@Param("after") int after);
		int countToTotalManager();
	 	
		//��˱�����ѵ
		int updateCultivateRecordStatus(@Param("recordId") int recordId,@Param("status") String status);
		
		//���ݹ��Ų�ѯ������������ѵid
		Integer[] selectIdByJobId(@Param("jobId") int jobId);
}
