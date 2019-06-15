package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.epms.Bean.CultivateApply;
import com.epms.Service.CultivateApplyService;

@Controller
@RequestMapping(value = "CultivateApplyController")
public class CultivateApplyController {
	@Autowired
	private CultivateApplyService cultivateApplyService;

	@Autowired
	private CultivateApply cultivateApply;

	// �ύ��ѵ�ƻ�
	@RequestMapping(value = "insertCultivateApply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody
	String insertCultivateApply(@Valid CultivateApply cultivateApply,
			BindingResult error, HttpSession session) {
		if (cultivateApply.getIntroduce().length() <= 0
				|| cultivateApply.getCost() <= 0
				|| cultivateApply.getSum() <= 0) {
			return null;
		}
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		cultivateApply.setWriteId(jobId);
		String result = cultivateApplyService.insertCultivateApply(
				cultivateApply, jobId);
		session.setAttribute("cultivateApply", cultivateApply);
		return result;
	}

	// �¼���ѯ�Լ��ύ���ϼ���ȫ����ѵ�ƻ�
	@RequestMapping(value = "/selectAllCultivateApplyByWriteId", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllRecruitByWriteId(int page, int limit, HttpSession session) {
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateApply> cultivateApplylist = cultivateApplyService
				.selectAllCultivateApplyByWriteId(before, limit, jobId);
		int count = cultivateApplyService
				.countSelectAllCultivateApplyByWriteId(jobId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(cultivateApplylist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

	// �ϼ���ѯ�¼��ύ����ѵ�ƻ�
	@RequestMapping(value = "/selectAllCultivateApply", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllCultivateApply(int page, int limit, HttpSession session) {
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateApply> cultivateApplylist = cultivateApplyService
				.selectAllCultivateApply(before, limit, jobId);
		int count = cultivateApplyService.countAllCultivateApply(jobId);
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(cultivateApplylist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

	// �����Ƹ�ƻ�
	@RequestMapping(value = "updateCultivateApplyStatus", method = RequestMethod.POST)
	public @ResponseBody
	String updateCultivateApplyStatus(CultivateApply cultivateApply) {
		return cultivateApplyService.updateCultivateApplyStatus(cultivateApply);
	}

	// Ա����ѯ��ѵ�ƻ�
	@RequestMapping(value = "/selectAllCultivateApplyToEmployee", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllCultivateApplyToEmployee(int page, int limit) {
		int before = limit * (page - 1);
		// �����������ݿ����ѯ�����ŵ�eilist�ļ�����
		List<CultivateApply> cultivateApplylist = cultivateApplyService
				.selectAllCultivateApplyToEmployee(before, limit);
		int count = cultivateApplyService
				.countSelectAllCultivateApplyToEmployee();
		// ��json����ֵ
		JSONArray json = JSONArray.fromObject(cultivateApplylist);
		String js = json.toString();
		// תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

}
