package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Rights;
import com.hjh.Bean.User;
import com.hjh.Mapper.RightsMapper;
import com.hjh.Service.RightsService;
@Service("rightsService")
public class RightsServiceImpl implements RightsService{

	@Autowired
	private RightsMapper rightsMapper;

	//��ѯ����Ȩ��
	public List<Rights> showRights(String menuName, int before, int after) {
		return rightsMapper.showRights(menuName,before,after);
	}

	//��������Ȩ������
	public int count(String menuName) {
		return rightsMapper.count(menuName);
	}

	//��ѯ���ڸ�Ȩ�����Ա��
	public List<User> showRightsPeople(String id,String jobId,String name,String dename, int before, int after) {
		return rightsMapper.showRightsPeople(id,jobId,name,dename,before,after);
	}

	//��ȡ���ڸ�Ȩ�����Ա��������
	public int countRightsPeople(String id,String jobId,String name,String dename) {
		return rightsMapper.countRightsPeople(id,jobId,name,dename);
	}

	//��Ȩ���������Ա��
	public int addRightsPeople(String jobId, String id) {
		return rightsMapper.addRightsPeople(jobId,id);
	}

	//��ѯ��Ȩ�����Ȩ��
	public List<Rights> showGroupsRights(String id, int before, int after) {
		return rightsMapper.showGroupsRights(id,before,after);
	}

	//��ѯ��Ȩ�����Ȩ�޵�����
	public int countGroupsRights(String id) {
		return rightsMapper.countGroupsRights(id);
	}

	//��ѯ��Ȩ������û�е�Ȩ��
	public List<Rights> showGroupsLackRights(String id,String menuName, int before, int after) {
		return rightsMapper.showGroupsLackRights(id,menuName,before,after);
	}

	//��ѯ��Ȩ������û�е�Ȩ�޵�����
	public int countGroupsLackRights(String id,String menuName) {
		return rightsMapper.countGroupsLackRights(id,menuName);
	}

	//��Ȩ���������Ȩ��
	public int addGroupsLackRights(String menuId, String id) {
		return rightsMapper.addGroupsLackRights(menuId,id);
	}

	//ɾ��Ȩ�����е�Ȩ��
	public int deleteGroupsRights(String menuId, String id) {
		return rightsMapper.deleteGroupsRights(menuId,id);
	}

	//��ѯȨ�����Ƿ����и�Ȩ��
	public Rights selectGroupsRights(String menuId, String roleId) {
		return rightsMapper.selectGroupsRights(menuId,roleId);
	}



	
}
