package com.epms.ServiceImpl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Menu;
import com.epms.Mapper.MenuMapper;
import com.epms.Service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	
	/*
	 * ͨ��Ȩ������Ҳ˵�
	 */
	public List<Menu> findMenuByRole(int jobId){
		return menuMapper.findMenuByRole(jobId);
	}

	//��֤Ȩ��
	public int findMenuByJobId(int jobId,String path) {
		return menuMapper.findMenuByJobId(jobId,path);
	}
}
