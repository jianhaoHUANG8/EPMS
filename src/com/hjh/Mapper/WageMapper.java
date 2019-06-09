package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Wage;
import com.hjh.Bean.WelfareUser;
@Repository
public interface WageMapper {

	//�鿴���˹���
	public List<Wage> showWageByJobId(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//�鿴���˹�������
	public int countByJobId(int jobId);

}
