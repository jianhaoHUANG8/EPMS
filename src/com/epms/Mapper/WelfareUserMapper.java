package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.WelfareUser;

@Repository
public interface WelfareUserMapper {

	//��ѯ���˸���
	public List<WelfareUser> showWelfare(@Param("jobId")int jobId,@Param("before")int before,@Param("after") int after);

	//��ѯ���˸�������
	public int count(int jobId);

}
