package com.hjh.Mapper;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hjh.Bean.Menu;

@Repository
public interface MenuMapper {

	/*
	 * ͨ��Ȩ�����ѯ�˵�
	 */
	List<Menu> findMenuByRole(int jobId);
}
