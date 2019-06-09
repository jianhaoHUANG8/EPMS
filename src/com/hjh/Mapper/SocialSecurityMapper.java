package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.SocialSecurity;

@Repository
public interface SocialSecurityMapper 
{
	//���ݹ��Ų�ѯ������Ϣ
	public List<SocialSecurity> selectSocialSecurityById (@Param("securityType") String securityType,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	public int count (@Param("securityType") String securityType,@Param("jobId")int jobId);
	
	//���ݹ��Ų�ѯ������Ϣ
	List<SocialSecurity> selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity);
}
