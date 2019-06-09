package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Rights;
import com.hjh.Bean.User;

public interface RightsService {

	//��ѯ����Ȩ��
	public List<Rights> showRights(String menuName, int before, int after);

	//��������Ȩ������
	public int count(String menuName);
	//��ѯ���ڸ�Ȩ�����Ա��
	public List<User> showRightsPeople(String id,String jobId,String name,String dename, int before, int after);
	//��ȡ���ڸ�Ȩ�����Ա��������
	public int countRightsPeople(String id,String jobId,String name,String dename);
	//��Ȩ���������Ա��
	public int addRightsPeople(String jobId, String id);
	//��ѯ��Ȩ�����Ȩ��
	public List<Rights> showGroupsRights(String id, int before, int after);
	//��ѯ��Ȩ�����Ȩ�޵�����
	public int countGroupsRights(String id);
	//��ѯ��Ȩ������û�е�Ȩ��
	public List<Rights> showGroupsLackRights(String id,String menuName, int before, int after);
	//��ѯ��Ȩ������û�е�Ȩ�޵�����
	public int countGroupsLackRights(String id,String menuName);
	//��Ȩ���������Ȩ��
	public int addGroupsLackRights(String menuId, String id);
	//ɾ��Ȩ�����е�Ȩ��
	public int deleteGroupsRights(String menuId, String id);
	//��ѯȨ�����Ƿ����и�Ȩ��
	public Rights selectGroupsRights(String menuId, String id);

}
