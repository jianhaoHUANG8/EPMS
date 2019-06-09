package com.hjh.ServiceImpl;

import java.util.List;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hjh.Bean.User;
import com.hjh.Mapper.UserMapper;
import com.hjh.Service.UserService;

@Service("userService")

public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	
	
	/*
	 * ��ѯ�����û�
	 */
	public List<User> findAllUser(String jobId,String name,String departmentName,int before,int after){
		return userMapper.findAllUser(jobId,name,departmentName,before,after);
	}

	/*
	 * ��ѯ����
	 */
	public int count(String jobId,String name,String departmentName) {
		return userMapper.count(jobId,name,departmentName);
	}
	
	/*
	 * ��ѯ��¼���û��Ƿ���ڣ����鹤�š����룩
	 */
	@Override
	public User checkLogin(int jobId,String password) {
		User user=userMapper.findByJobId(jobId);
		if(user!=null&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}
	
	/*
	 * ��ȡ�û�������
	 */
	public String findNameByJobId(int jobId){
		return userMapper.findNameByJobId(jobId);
	}
	
	/*
	 * ��ѯ�û��Ƿ���ڣ�ֻ��jobId��
	 */
	public User findAccountByJobId(int jobId){
		return userMapper.findAccountByJobId(jobId);
	}
	/*
	 * ������û�
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int addAccount(int jobId,String name,int denameId, String password){
		int i=0,j=0;
		try{
			i=userMapper.addUser(jobId,password);
			j=userMapper.addPersonalInfo(jobId,name,denameId);
			if(i==0||j==0){
				throw new Exception();
			}
		}catch(Exception e){
			System.out.println("���ݲ�������");
		}
		return i+j;
	}
	
	/*
	 * �޸��˺������ְ���
	 */
	@Transactional
	public int editAccount(int jobId,String password,String state,String denameId){
		try{
			userMapper.updatePersonalinfo(jobId,denameId);
			userMapper.editAccount(jobId,password,state);
			return 1;
		}catch(Exception e){
			System.out.println("���ݲ�������");
			throw new RuntimeSqlException();
		}
	}

	//��������
	@Transactional(rollbackFor=Exception.class)
	public void test(int jobId,String password){
		try{
			userMapper.addUser(jobId, password);
			throw new RuntimeSqlException();
		}catch(Exception e){
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("���ݲ�������");
			throw new RuntimeSqlException();
		}
		
		
	}

	//ͨ�������ȡ����
	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	
	
	

	
}
