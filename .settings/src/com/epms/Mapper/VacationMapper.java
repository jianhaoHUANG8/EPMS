package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Vacate;
import com.epms.Bean.Vacation;

@Repository
public interface VacationMapper 
{
	//��ѯ���м���ʣ�����
	List<Vacation> selectAllRemainById (@Param("type") String type,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int count (@Param("type") String type,@Param("jobId")int jobId);
	
	//����id�����Ͳ�ѯʣ�����
	Vacation selectRemainByVacate(Vacate vacate);
	Vacation selectRemainByVacation(Vacation vacation);
	
	//����id�������޸�����ʣ�����
	int updateVirtualUse(Vacation vacation);
	
	//����id�������޸�����ʣ���������ʵʣ�����
	int updateVirtualAll(Vacation vacation);
	
	////����id�������޸�����ʣ��������Ѿ�ʹ�����
	int updateVirtualExceptRemain(Vacation vacation);
}
