package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Role;

@Repository
public interface RoleMapper {

	//��ѯ�û�Ȩ����
	public List<Role> showRightsGroups(@Param("before")int before,@Param("after")int after);
	
	//����������
	public int count();

	//ͨ��Ȩ��������Ȩ����
	public int findGroupsByName(String name);

	//����Ȩ����
	public int addRightsGroups(@Param("name")String name,@Param("content") String content,@Param("createId") int createId,@Param("createDate")String createDate);

	//����Ȩ����
	public int editRightsGroups(@Param("name")String name,@Param("content") String content,@Param("oldName")String oldName);

	//��ѯȨ��������Ա
	public List<String> showRightsGroupsPeople(@Param("id")String id,@Param("before") int before,@Param("after")int after);

	//����Ȩ��������Ա����
	public int countPeople(String id);

	//ɾ��Ȩ�����е���Ա
	public int deleteRightsGroupsPeople(@Param("jobId")int jobId,@Param("rName")String rName);
	//Ϊ�½��û�����Ĭ��Ȩ����
	public int addUserToRole(@Param("jobId")int jobId);
	//ɾ��Ȩ�������Ȩ����
	public int deleteRole(String rName);
	//ɾ��Ȩ����-Ȩ����Ȩ����
	public int deleteRoleMenu(String rName);
	//ɾ��Ȩ����-�û���Ȩ����
	public int deleteUserRole(String rName);
}