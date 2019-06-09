package com.hjh.Service;

import java.util.List;

















import com.hjh.Bean.User;

public interface UserService {

	
	//��ѯ�����û�
	public List<User> findAllUser(String jobId,String name,String departmentName,int before,int after);
	//����������
	public int count(String jobId,String name,String departmentName);
	//��ѯ��¼���û��Ƿ����
	public User checkLogin(int jobId, String password);
	//��ȡ�û�������
	public String findNameByJobId(int jobId);
	//������û�
	public int addAccount(int jobId,String name,int denameId,String password);
	//��ѯ�û��Ƿ����
	public User findAccountByJobId(int jobId);
	//�޸��˺������ְ���
	public int editAccount(int jobid, String password,String state,String denameId);
	
	//��������
	public void test(int jobId,String password) ;
	//ͨ�������ȡ����
	public User getUserByEmail(String email);


	
	
}
