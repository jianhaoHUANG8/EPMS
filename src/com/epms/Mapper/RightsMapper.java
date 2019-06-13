package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Rights;
import com.epms.Bean.User;

@Repository
public interface RightsMapper {

	//��ѯ����Ȩ��
	public List<Rights> showRights(@Param("menuName")String menuName,@Param("before") int before,@Param("after") int after);
	
	//��������Ȩ������
	public int count(@Param("menuName")String menuName);

	//��ѯ���ڸ�Ȩ�����Ա��
	public List<User> showRightsPeople(@Param("id")String roleId,@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName,@Param("before") int before,@Param("after") int after);

	//��ȡ���ڸ�Ȩ�����Ա��������
	public int countRightsPeople(@Param("id")String id,@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName);

	//��Ȩ���������Ա��
	public int addRightsPeople(@Param("jobId")String jobId,@Param("id") String id);

	//��ѯ��Ȩ�����Ȩ��
	public List<Rights> showGroupsRights(@Param("id")String id,@Param("before") int before,@Param("after") int after);

	//��ѯ��Ȩ�����Ȩ�޵�����
	public int countGroupsRights(@Param("id")String id);

	//��ѯ��Ȩ�����Ȩ��
	public List<Rights> showGroupsRights(@Param("id")String id,@Param("menuName") String menuName,@Param("before")int before,@Param("after") int after);
	
	//��ѯ��Ȩ�����Ȩ�޵�����
	public int countGroupsRights(@Param("id")String id,@Param("menuName") String menuName);

	//��ѯ��Ȩ������û�е�Ȩ��
	public List<Rights> showGroupsLackRights(@Param("id")String id,@Param("menuName") String menuName,@Param("before")int before,@Param("after") int after);

	//��ѯ��Ȩ������û�е�Ȩ�޵�����
	public int countGroupsLackRights(@Param("id")String id,@Param("menuName") String menuName);

	//��Ȩ���������Ȩ��
	public int addGroupsLackRights(@Param("menuId")String menuId,@Param("id") String id);
	
	//ɾ��Ȩ�����е�Ȩ��
	public int deleteGroupsRights(@Param("menuId")String menuId,@Param("id") String id);

	//��ѯȨ�����Ƿ����и�Ȩ��
	public Rights selectGroupsRights(@Param("menuId")String menuId,@Param("roleId") String roleId);
}
