package com.epms.Service;

import java.util.List;

import com.epms.Bean.Role;

public interface RoleService {

	//��ѯ�û�Ȩ����
	public List<Role> showRightsGroups(int before,int after);
	//����������
	public int count();
	//ͨ��Ȩ��������Ȩ����
	public int findGroupsByName(String name);
	//���Ȩ����
	public int addRightsGroups(String name, String content, int createId,String nowTime);
	//����Ȩ����
	public int editRightsGroups(String name, String content,String oldName);
	//��ѯȨ��������Ա
	public List<String> showRightsGroupsPeople(String id, int before, int after);
	//����Ȩ��������Ա����
	public int countPeople(String id);
	//ɾ��Ȩ�����е���Ա
	public int deleteRightsGroupsPeople(int jobId,String rName);
	
}
