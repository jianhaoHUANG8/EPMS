package com.epms.Controller;

import java.util.List;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.epms.Bean.Menu;
import com.epms.Service.MenuService;
@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	/*
	 * ��̬���ɲ˵���
	 */
	@RequestMapping(value="/showMenuByRole",method={RequestMethod.POST},produces="application/json; charset=utf-8")
	public @ResponseBody String showMenuByRole(int jobId){
		//��ѯ���û���ӵ�еĵĲ˵�
		List<Menu> menu=menuService.findMenuByRole(jobId);
		//����ѯ����ת��ΪJSON��ʽ
		JSONArray json=JSONArray.fromObject(menu);
		String js=json.toString();
		return js;
	}
	
	/*
	 * ������Ҫ��¼״̬��jspҳ��
	 */
	@RequestMapping(value="requestPage")
	public ModelAndView requestPage(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}
	
	/*
	 * ���ز���Ҫ��¼״̬��jspҳ��
	 */
	@RequestMapping(value="requestPageNoLogin")
	public ModelAndView requestPageNoLogin(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}
	
	/*
	 * �������˵�ҳ��
	 */
	@RequestMapping(value="requestMainPage")
	public ModelAndView requestMainPage(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}

}
