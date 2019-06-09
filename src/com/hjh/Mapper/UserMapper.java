package com.hjh.Mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.User;

@Repository
public interface UserMapper {

   /*
    * ��ҳ����
    */
	public List<User> findAllUser(@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName,@Param("before") int before,@Param("after") int after);
	public int count(@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName);
	
	//��ѯ��¼���û��Ƿ����
	public User findByJobId(int jobId);
	//��ѯ�û��Ƿ����
	public User findAccountByJobId(int jobId);
	//��ȡ�û�������
	public String findNameByJobId(int jobId);
	//�޸��˺������ְ���
	public int editAccount(@Param("jobId")int jobId,@Param("password")String password,@Param("state")String state);
	
	//�������˺�
	public int addUser(@Param("jobId")int jobId,@Param("password")String password); 
	//����¸�����Ϣ
	public int addPersonalInfo(@Param("jobId")int jobId,@Param("name") String name,@Param("denameId") int denameId);
	//ȡ���ܾ�����
	public User findGeneralManager();
	//ȡ�����ž�����
	public List<User> findManager();
	//ȡ���ò��ž����ڵ�Ա������
	public List<User> findStaff(@Param("MjobId")String MjobId);
	//ȡ����Ա��������ͬ�µĹ���
	public List<User> findColleague(@Param("SjobId")String SjobId);
	//ͨ�������ȡ����
	public User getUserByEmail(@Param("email")String email);
	//ͨ�������޸Ĳ���
	public int updatePersonalinfo(@Param("jobId")int jobId,@Param("denameId") String denameId);


	int updateState(@Param("jobId")int jobId,@Param("state")String state);

}
