package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Role;

@Repository
public interface RoleMapper {

	//��ѯ�û�Ȩ����
	public List<Role> showRightsGroups(@Param("before")int before,@Param("after")int after);
	
	//����������
	public int count();

	//ͨ��Ȩ��������Ȩ����
	public int findGroupsByName(String name);

	//���Ȩ����
	public int addRightsGroups(@Param("name")String name,@Param("content") String content,@Param("createId") int createId,@Param("createDate")String createDate);

	//����Ȩ����
	public int editRightsGroups(@Param("name")String name,@Param("content") String content,@Param("oldName")String oldName);

	//��ѯȨ��������Ա
	public List<String> showRightsGroupsPeople(@Param("id")String id,@Param("before") int before,@Param("after")int after);

	//����Ȩ��������Ա����
	public int countPeople(String id);

	//ɾ��Ȩ�����е���Ա
	public int deleteRightsGroupsPeople(@Param("jobId")int jobId,@Param("rName")String rName);

	
}
