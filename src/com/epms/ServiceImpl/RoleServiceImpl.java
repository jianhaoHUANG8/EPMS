package com.epms.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Role;
import com.epms.Mapper.RoleMapper;
import com.epms.Service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	//��ѯ�û�Ȩ����
	public List<Role> showRightsGroups(int before,int after){
		return roleMapper.showRightsGroups(before,after);
	}
	
	//����������
	public int count(){
		return roleMapper.count();
	}

	//ͨ��Ȩ��������Ȩ����
	public int findGroupsByName(String name) {

		return roleMapper.findGroupsByName(name);
	}

	//���Ȩ����
	public int addRightsGroups(String name, String content, int createId,String createDate) {
		return roleMapper.addRightsGroups(name,content,createId,createDate);
	}

	//����Ȩ����
	public int editRightsGroups(String name, String content,String oldName) {
		return roleMapper.editRightsGroups(name,content,oldName);
	}
	
	//��ѯȨ��������Ա
	public List<String> showRightsGroupsPeople(String id,int before,int after){
		return roleMapper.showRightsGroupsPeople(id,before,after);
	}

	//����Ȩ��������Ա����
	public int countPeople(String id) {
		return roleMapper.countPeople(id);
	}

	//ɾ��Ȩ�����е���Ա
	public int deleteRightsGroupsPeople(int jobId,String rName) {
		return roleMapper.deleteRightsGroupsPeople(jobId,rName);
	}
}
