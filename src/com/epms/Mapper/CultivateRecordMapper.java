package com.epms.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//��Ƹ�ƻ�����
@Repository
public interface CultivateRecordMapper 
{
	//���ݹ��Ų�ѯ������������ѵid
	Integer[] selectIdByJobId(@Param("jobId") int jobId);
}
