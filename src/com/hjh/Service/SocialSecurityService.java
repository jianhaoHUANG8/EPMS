package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.SocialSecurity;

public interface SocialSecurityService
{
	//���ݹ��Ų�ѯ������Ϣ
	public List<SocialSecurity> selectSocialSecurityById(String securityType,int before,int after,int jobId);	
    public int count (String securityType,int jobId);

	//���ݹ��Ų�ѯ������Ϣ
	public List<SocialSecurity> selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity);
}
