package com.epms.Mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epms.Bean.Menu;

@Repository
public interface MenuMapper {

	/*
	 * ͨ��Ȩ�����ѯ�˵�
	 */
	List<Menu> findMenuByRole(int jobId);
}
