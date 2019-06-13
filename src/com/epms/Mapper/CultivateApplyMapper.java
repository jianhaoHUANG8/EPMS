package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.CultivateApply;
//��Ƹ�ƻ�����
@Repository
public interface CultivateApplyMapper 
{
	//����id��ѯ��Ӧ����ѵ��ʼ�ͽ���ʱ��
	List<CultivateApply> selectTheSameDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameStartDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameFinishDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
}
