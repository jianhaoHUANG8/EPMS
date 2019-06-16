package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Personalinfo;
import com.epms.Bean.TotalData;
import com.epms.Bean.Vacate;
import com.epms.Bean.Vacation;
import com.epms.Bean.WorkingCalendar;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.TotalDataMapper;
import com.epms.Mapper.VacateMapper;
import com.epms.Mapper.VacationMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.VacateService;
import com.epms.Utils.CalculateDaySum;

@Service("vacateService")
public class VacateServiceImpl implements VacateService
{
	@Autowired
	private Vacation vacation;
	
	@Autowired
	private TotalData totalData;
	
	@Autowired
	private VacateMapper vacateMapper;
	
	@Autowired
	private VacationMapper vacationMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	//�ύ�������
	@Override
	public String insertVacate(Vacate vacate) 
	{
		JSONObject result = new JSONObject();
		vacate.setState("�����");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		vacate.setSubmitDate(dateFormat.format(new Date()));

		if(CheckErrorMess(vacate)==null)//������������Ƿ���ڴ���
		{
			// �����������
			vacate = calculateVacateDay(vacate);
			
			// �ύ���������
			vacateMapper.insertVacate(vacate);

			// �����ύ��������޸���Ӧ���͵ļ���ʣ����Ϣ
			/*if (vacate.getType().equals("���") || vacate.getType().equals("����")) 
			{
				Vacation vacation = vacationMapper.selectRemainByVacate(vacate);
				int virtualUse = vacation.getVirtualUse();// ��ô�ʱ��Ӧ���͵ļ�������ʹ������
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				vacation.setVirtualUse(vacate.getDaySum() + virtualUse);
				vacationMapper.updateVirtualUse(vacation);
			}*/
			Vacation vacation = vacationMapper.selectRemainByVacate(vacate);
			int virtualUse = vacation.getVirtualUse();// ��ô�ʱ��Ӧ���͵ļ�������ʹ������
			vacation.setJobId(vacate.getJobId());
			vacation.setType(vacate.getType());
			vacation.setVirtualUse(vacate.getDaySum() + virtualUse);
			vacationMapper.updateVirtualUse(vacation);
			result.put("status", true);
			result.put("message", "�ύ�ɹ���");
		} else {
			return CheckErrorMess(vacate);
		}
		return result.toString();
	}
	
	//����ύ������Ƿ����
	public String CheckErrorMess(Vacate vacate)
	{
		int startId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
		int nowId=workingCalendarMapper.selectIdByDate(vacate.getSubmitDate());
		JSONObject result = new JSONObject();
		
		if(vacate.getType().equals(""))//��ѯ����������Ƿ�Ϊδ��������
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ���δѡ��������ͣ�");
		}
		else if(calculateVacateDay(vacate)==null)//��������������ж�����Ŀ�ʼʱ��ͽ���ʱ���Ƿ����
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ����ڽ������ڱ����ڼ��ڿ�ʼ����֮��");
		}
		else if(startId<nowId)//��ѯ����������Ƿ�Ϊδ��������
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ�������һ��δ����������ڣ�");
		}
		else if(vacateMapper.selectIfRepeat(vacate)>0)//�ж��Ƿ��ظ��ύ
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ������ظ��ύ��");
		}
		else if(vacate.getDiscountDay()==0)//��ѯ��ٵ������Ƿ���ڷǼ�������
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ����ύ�����ʱ��Ϊ�����ڼ��գ�������٣�");
		}
		else if(!checkSexIfHavePregnancy(vacate))//�����Ϊ����ʱ������Ա��Ƿ�Ϊ��
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ�����û�в��٣�");
		}
		else if(!checkRemain(vacate))//�жϼ����Ƿ����
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ����ڲ��㣡");
		}
		else if(!checkVacateDateIfRepeat(vacate))
		{
			result.put("status", false);
			result.put("message", "�ύʧ�ܣ��ύ��������������ύ(��ͬ�������)���������ʱ���ͻ��");
		}
		else
		{
			return null;
		}
		return result.toString();
	}
	
	//��ѯ�Ƿ����㹻ʣ���������
	public Boolean checkRemain(Vacate vacate)
	{
		//��ô�ʱ��Ӧ�ļ���ʣ��������Ϣ(����ٺͲ������ѯ)
		if(vacate.getType().equals("���")||vacate.getType().equals("���"))
		{
			Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
			int virtualUse=vacation.getVirtualUse();
			int remain=vacation.getRemain();
			if((remain-virtualUse)>=vacate.getDaySum())
			{
				return true;
			}
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//ͨ���Ա��ж��Ƿ��в���
	public Boolean checkSexIfHavePregnancy(Vacate vacate)
	{
		if(vacate.getType().equals("����"))
		{
			Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(vacate.getJobId());
			if(personalinfo.getSex().equals("��"))
			{ return false; }
			else {
				return true;}
		} else{
			return true;
		}
	}
	
	//������������ͼ���۹��ʵ�����
	public Vacate calculateVacateDay(Vacate vacate)
	{
		//�����������(�������а����ķ����ڼ�������)
		CalculateDaySum cds=new CalculateDaySum();
		vacate.setDaySum(cds.calculate(vacate.getStartDate(), vacate.getFinishDate()));
		if(vacate.getDaySum()<=0)
		{ return null; }
		else{
			//����ʵ�ʿ۹��ʵ�����(����ȥ�����ż�����)
			int startId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
			int finishId=workingCalendarMapper.selectIdByDate(vacate.getFinishDate());
			int notWorkDay=workingCalendarMapper.selectNotWorkBetweenDate(startId,finishId);
			vacate.setDiscountDay(vacate.getDaySum()-notWorkDay);
			return vacate;
		}
	}
	
	//�����ύ����������Ƿ���֮ǰ�Ѿ�������ˣ��������ʱ���ͻ
	public boolean checkVacateDateIfRepeat(Vacate vacate)
	{
		//�ȼ���������ݿ����Ա����ͨ�������ʱ��
		List<Vacate> vacateList=vacateMapper.selectVacateStartAndFinishByJobId(vacate.getJobId());
		int vacateStartId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
		int vacateFinishId=workingCalendarMapper.selectIdByDate(vacate.getFinishDate());
		
		List dateIdlist = new ArrayList();
		for(int i=0;i<vacateList.size();i++)
		{
			int startId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getStartDate());
			int finishId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getFinishDate());
			List<WorkingCalendar> intList=workingCalendarMapper.selectIdBetweenDate(startId, finishId);
			for(int j=0;j<intList.size();j++)
			{
				dateIdlist.add(intList.get(j).getId());
			}
		}
		for(int i=0;i<dateIdlist.size();i++)
		{
			if(Integer.parseInt(dateIdlist.get(i).toString())==vacateStartId
					||Integer.parseInt(dateIdlist.get(i).toString())==vacateFinishId)
			{
				return false;
			}
		}
		return true;
	}
	
	//��ѯ���ݹ��Ų�ѯȫ�������Ϣ
	@Override
	public List<Vacate> selectAllVacateApplyByJobId(String state,String type,int before, int after, int jobId) {
		return vacateMapper.selectAllVacateApplyByJobId(state, type, before, after, jobId);
	}

	@Override
	public int count(String state,String type,int jobId) {
		return vacateMapper.count(state, type, jobId);
	}

	//��ѯȫ��ֱ���¼��ύ�������������(�ϼ���ѯ������)
	@Override
	public List<Vacate> selectAllVacateApply(String department_id,String state,String type,
			int before, int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			return vacateMapper.selectVacateToManager(department_id, state, type, before, after, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return vacateMapper.selectVacateToTotalManager(department_id, state, type, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return vacateMapper.selectVacateToBoard(department_id, state, type, before, after);
		}
		return null;
	}

	@Override
	public int countToLeader(String department_id,String state,String type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("���ž���"))
		{
			return vacateMapper.countToManager(department_id, state, type, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("�ܾ���"))
		{
			return vacateMapper.countToTotalManager(department_id, state, type);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("����"))
		{
			return vacateMapper.countToBoard(department_id, state, type);
		}
		return 0;
	}

	//�����������
	@Override
	public String updateVacate(Vacate vacate) 
	{
		// �ȼ���Ƿ��Ѿ�������������������,���޷���������
		if (vacateMapper.selectVacateById(vacate.getId()).getApprovalDate() == null) 
		{
			if (vacateMapper.selectVacateById(vacate.getId()).getType().equals("���") 
					|| vacateMapper.selectVacateById(vacate.getId()).getType().equals("���")) 
			{
				//��������״̬���޸���Ӧ���͵ļ���ʣ����Ϣ
				vacate=vacateMapper.selectVacateById(vacate.getId());
				Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
				int virtualUse=vacation.getVirtualUse();//��ô�ʱ��Ӧ���͵ļ�������ʹ������
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				
				if(vacate.getState().equals("��ͬ��"))
				{	
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacationMapper.updateVirtualUse(vacation);
				}
				else
				{
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacation.setRemain(vacation.getRemain()-vacate.getDaySum());
					vacation.setAlreadyUse(vacation.getAlreadyUse()+vacate.getDaySum());
					vacationMapper.updateVirtualAll(vacation);
				}
			}
			else
			{
				//��������״̬���޸���Ӧ���͵ļ���ʣ����Ϣ
				vacate=vacateMapper.selectVacateById(vacate.getId());
				Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
				int virtualUse=vacation.getVirtualUse();//��ô�ʱ��Ӧ���͵ļ�������ʹ������
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				
				if(vacate.getState().equals("��ͬ��"))
				{	
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacationMapper.updateVirtualUse(vacation);
				}
				else
				{
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacation.setAlreadyUse(vacation.getAlreadyUse()+vacate.getDaySum());
					vacationMapper.updateVirtualExceptRemain(vacation);
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			vacate.setApprovalDate(dateFormat.format(new Date()));
			vacateMapper.updateVacate(vacate);//�޸�����״̬
			return "true";
		} else {
			return "false";
		}
	}
	
	//���ݹ��Ų�ѯ��ͬ������������Ϣ
	@Override
	public List<Vacate> selectPassVacateByJobId(int before, int after, int jobId) 
	{
		return vacateMapper.selectPassVacateByJobId(before, after, jobId);
	}

	@Override
	public int countPassVacateByJobId(int jobId) 
	{
		return vacateMapper.countPassVacateByJobId(jobId);
	}
	
	//���ٴ���
	@Override
	public String cancelVacateApply(Vacate vacate) 
	{
		JSONObject result =new JSONObject();
		vacate=vacateMapper.selectVacateById(vacate.getId());
		// �ȼ���Ƿ��Ѿ����ٹ����������ٹ�,���޷���������
		if (vacate.getCancalDate()== null) 
		{
			if(!vacate.getState().equals("ͬ��"))
			{
				result.put("status", false);
				result.put("message", "����ʧ�ܣ��������뻹δͬ�⣡");
			}
			else
			{
				CalculateDaySum cds=new CalculateDaySum();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String today=dateFormat.format(new Date());
				//�����Ƿ��������(�����ڼ��ڽ�������֮��ſ����٣�
				if(cds.calculate(vacate.getFinishDate(),today)>=0)
				{
					vacate.setCancalDate(today);
					vacateMapper.cancelVacateApply(vacate);// �޸�����״̬
					result.put("status", true);
					result.put("message", "���ٳɹ���");
				}
				else
				{
					result.put("status", false);
					result.put("message", "����ʧ�ܣ�δ��������ʱ�䣡");
				}
			}
		} 
		else 
		{
			result.put("status", false);
			result.put("message", "����ʧ�ܣ������ظ����٣�");
		}
		return result.toString();
	}
	
	//��ѯȫ����Ա���µ��������
	@Override
	public List<TotalData> selectAllVacateSum(String year,String month) 
	{
		//��ɾ������ȫ������
		totalDataMapper.deleteAll();
		
		//1�������ٿ�ʼʱ�����ٽ���ʱ�䶼�Ǳ��µ��������
		List<Vacate> vacateList=vacateMapper.countVacateSumBySameDate(year,month);
		
		for(int i=0;i<vacateList.size();i++)//�����Ǳ��µ������������totalData����
		{
			totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(), vacateList.get(i).getDaySum());
		}
		
		//�����������һ������ڣ��Ͳ�����µ�һ������ڣ����ڼ�����·��������Ķ�Ӧ�ĵ����������
		String firstDay=workingCalendarMapper.selectMonthFirstDate(year,month);
		int firstId=workingCalendarMapper.selectIdByDate(firstDay);
		String lastDay=workingCalendarMapper.selectMonthLastDate(year,month);
		int lastId=workingCalendarMapper.selectIdByDate(lastDay);
		
		//2�����������ͬ�·ݵ��������(��ٿ�ʼʱ��Ϊ����,������ʱ�䲻Ϊ����)
		vacateList=vacateMapper.countVacateSumByStartIsMonth(year,month);
		if(!vacateList.isEmpty())//�ж��Ƿ���ڴ���������������
		{
			for(int i=0;i<vacateList.size();i++)
			{
				//��ô�������Ŀ�ʼʱ�䣬���������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData=totalDataMapper.selectByJobId(vacateList.get(i).getJobId());
				int startId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getStartDate());
				int day=workingCalendarMapper.selectWorkBetweenDate(startId, lastId);
				//�ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if(totalData==null)
				{
					totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(),day);
				}
				else
				{
					totalDataMapper.updateTotalDataByTotalVacate(vacateList.get(i).getJobId(),
					        totalData.getTotalVacate()+day);
				}
			}
		}
		
		//3�����������ͬ�·ݵ��������(��ٽ���ʱ��Ϊ����,����ʼʱ�䲻Ϊ����)
		vacateList=vacateMapper.countVacateSumByFinishIsMonth(year,month);
		if(!vacateList.isEmpty())
		{
			for(int i=0;i<vacateList.size();i++)
			{
				//��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData=totalDataMapper.selectByJobId(vacateList.get(i).getJobId()); 
				int finishId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getFinishDate());
				int day=workingCalendarMapper.selectWorkBetweenDate(firstId, finishId);
				//�ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if(totalData==null)
				{
					totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(),day);
				}
				else
				{
					totalDataMapper.updateTotalDataByTotalVacate(vacateList.get(i).getJobId(),
					        totalData.getTotalVacate()+day);
				}
			}
		}
		return  totalDataMapper.selectAllDataSum();
	}
}
