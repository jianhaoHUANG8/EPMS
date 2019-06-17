package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Attendance;
import com.epms.Bean.AttendanceRule;
import com.epms.Bean.TotalData;
import com.epms.Bean.WorkingCalendar;
import com.epms.Conventer.DateConverter;
import com.epms.Mapper.AttendanceMapper;
import com.epms.Mapper.AttendanceRuleMapper;
import com.epms.Mapper.TotalDataMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.AttendanceService;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService
{
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	@Autowired
	private AttendanceRuleMapper attendanceRuleMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	@Autowired
	private Attendance attendance;
	
	@Autowired
	private AttendanceRule attendanceRule;
	
	@Autowired
	private TotalData totalData;
	
	//�ϰ��
	public String insertInAttendance(int jobId) 
	{
		attendance.setJobId(jobId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		attendance.setToday(dateFormat2.format(date));
		if(ChackDate(attendance)==null)//����Ƿ����д򿨼�¼
		{
			attendance.setStartDate(dateFormat.format(date).toString());
			int nowTime=date.getHours()*60+date.getMinutes();//����ʱ�任��ɷ���
			attendance.setStartTime(nowTime);
			attendanceRule=attendanceRuleMapper.selectAttendanceRule();
			if(checkIfWorkingDay(attendance.getToday()))//�ж��Ƿ�Ϊ������
			{
				//�����ϰ�ٵ�ʱ��
				if(nowTime>Integer.parseInt(attendanceRule.getSetStart())*60)
				{
					attendance.setLately(nowTime-Integer.parseInt(attendanceRule.getSetStart())*60);
				} else{
					attendance.setLately(0);}
			}
			else//�ڼ��գ��޳ٵ�
			{
				attendance.setLately(0);
			}
			//ִ���ϰ��
			if(attendanceMapper.insertInAttendance(attendance)>0)
			{
				return "�ϰ�򿨳ɹ���";
			} else{
				return "�ϰ��ʧ�ܣ�";
			}
		} else{
			return "�����ظ��򿨣�";
		}
	}
	
	//�°��
	@Override
	public String insertOutAttendance(int jobId) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd ");
		attendance.setJobId(jobId);
		Date date=new Date();
		attendance.setToday(dateFormat2.format(date));
		if(ChackDate(attendance)!=null && ChackDate(attendance).getFinishDate()==null)
		{
			attendance.setJobId(jobId);
			attendance.setFinishDate(dateFormat.format(date).toString());
			attendanceRule=attendanceRuleMapper.selectAttendanceRule();
			
			int nowTime=date.getHours()*60+date.getMinutes();
			if(checkIfWorkingDay(attendance.getToday()))//�ж��Ƿ�Ϊ������
			{
				//�����°�Ӱ�ʱ��������ϰ�����ʱ��
				if(nowTime>Integer.parseInt(attendanceRule.getSetFinish())*60)
				{
					attendance.setOverTime(nowTime-Integer.parseInt(attendanceRule.getSetFinish())*60);
					attendance.setEarlyLeave(0);
				} else{
					attendance.setOverTime(0);
					attendance.setEarlyLeave(Integer.parseInt(attendanceRule.getSetFinish())*60-nowTime);
					}
			}
			else//�ڼ��գ�������
			{
				attendance.setEarlyLeave(0);
				//��ô��˵��ϰ�ʱ��
				Attendance attendance1=attendanceMapper.SelectAttendanceByTodayAndJobId(attendance);
				//����Ӱ�ʱ��
				attendance.setOverTime(nowTime-attendance1.getStartTime());
			}
			//ִ���°��
			if(attendanceMapper.insertOutAttendance(attendance)>0)
			{
				return "�°�򿨳ɹ���";
			}else {
				return "�°��ʧ��";
			}
		}else {
			return "���Ƚ����ϰ�򿨻��������´򿨣�";
		}
	}
	
	//����Ƿ����д򿨼�¼
	@Override
	public Attendance ChackDate(Attendance attendance) 
	{
		return attendanceMapper.SelectAttendanceByTodayAndJobId(attendance);
	}
	
	//�ж��Ƿ�Ϊ������(��Ϊ�����գ������ж��Ƿ����ˡ��ٵ����ڼ��մ�ֻ��Ӱ�ʱ��)
	public  boolean checkIfWorkingDay(String today)
	{
		if(workingCalendarMapper.checkIfWorkingDay(today)==0)//��Ϊ0��Ϊ�ڼ���
		{
			return false;
		}
		else//��Ϊ1��Ϊ�����գ������ٵ�������
		{
			return true;
		}
	}
	
	
	//ͨ�����Ų�ѯȫ���򿨼�¼
	@Override
	public List<Attendance> selectAllById(int before, int after, int jobId,String today) 
	{
		return attendanceMapper.selectAllById(before, after, jobId,today);
	}
	
	@Override
	public int count(int jobId,String today) {
		return attendanceMapper.count(jobId, today);
	}
	
	//��������ģ����ѯÿ��Ա���ĳٵ������ˡ��Ӱ�ʱ��
	/*@Override
	public List<Attendance> selectSumDataByDate(int before,int after,String startDate) 
	{
		return attendanceMapper.selectSumDataByDate(before, after, startDate);
	}

	@Override
	public int countSumDataByDate(String startDate) 
	{
		return attendanceMapper.countSumDataByDate(startDate);
	}
	*/
	
	//��ѯ����ÿ��Ա���ĳٵ�(����)������(����)���Ӱ�(�԰�СʱΪ��λ)ʱ��
	@Override
	public List<TotalData> selectAllSumDataByInDate(String year,String month) 
	{	
		//1����������յ�����
		String[] workingList=workingCalendarMapper.selectWorkDate(year, month);
		//�����Ӧ�����յ������е��ܵĳٵ������ˡ��Ӱ�ʱ��
		List<Attendance> earlyLeaveList=attendanceMapper.selectSumEarlyLeaveByInDate(workingList);
		List<Attendance> lateltList=attendanceMapper.selectSumLatelyByInDate(workingList);
		List<Attendance> overTimeList=attendanceMapper.selectSumOvertimeByInDate(workingList);
		detailWorkingDataByInDate(earlyLeaveList,lateltList,overTimeList);
		
		// 2������ڼ��յ�����
		String[] notWorkingList = workingCalendarMapper.selectNotWorkDate(year, month);
		// �����Ӧ�ڼ��յ������еļӰ�ʱ��(���ڽڼ���û�гٵ������ˣ�����ֻ��Ӱ�)
		overTimeList=attendanceMapper.selectSumOvertimeByInDate(notWorkingList);
		detailNotWorkingDataByInDate(overTimeList);
		return totalDataMapper.selectAllDataSum();
	}
	
	//�����յľ������
	public void detailWorkingDataByInDate(List<Attendance> earlyLeaveList,List<Attendance> lateltList,List<Attendance> overTimeList)
	{
		if (!earlyLeaveList.isEmpty())
		{
			for (int i = 0; i < earlyLeaveList.size(); i++) 
			{
				// ��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData = totalDataMapper.selectByJobId(earlyLeaveList.get(i).getJobId());
				// �ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByEarlyLeave(earlyLeaveList.get(i).getJobId(),
							earlyLeaveList.get(i).getEarlyLeave());
				} else {
					totalDataMapper.updateTotalDataByEarlyLeave(earlyLeaveList.get(i).getJobId(),
							totalData.getTotalEarlyleave()+ earlyLeaveList.get(i).getEarlyLeave());
				}
			}
		}
		if (!lateltList.isEmpty())
		{
			for (int i = 0; i < lateltList.size(); i++) 
			{
				// ��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData = totalDataMapper.selectByJobId(lateltList.get(i).getJobId());
				// �ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByLately(lateltList.get(i).getJobId(),lateltList.get(i).getLately());
				} else {
					totalDataMapper.updateTotalDataByLately(lateltList.get(i).getJobId(),
							totalData.getTotalLately()+lateltList.get(i).getLately());
				}
			}
		}
		if (!overTimeList.isEmpty())
		{
			for (int i = 0; i < overTimeList.size(); i++) 
			{
				// ��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData = totalDataMapper.selectByJobId(overTimeList.get(i).getJobId());
				// �ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByWorkOverTime(overTimeList.get(i).getJobId(),overTimeList.get(i).getOverTime()/30);
				} else {
					totalDataMapper.updateTotalDataByWorkOverTime(overTimeList.get(i).getJobId(),
							totalData.getTotalWorkOverTime()+overTimeList.get(i).getOverTime()/30);
				}
			}
		}
	}
	
	//�ڼ��յľ������
	public void detailNotWorkingDataByInDate(List<Attendance> overTimeList)
	{
		if (!overTimeList.isEmpty())
		{
			for (int i = 0; i < overTimeList.size(); i++) 
			{
				// ��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
				totalData = totalDataMapper.selectByJobId(overTimeList.get(i).getJobId());
				// �ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByNotWorkOverTime(overTimeList.get(i).getJobId(),overTimeList.get(i).getOverTime()/30);
				} else {
					totalDataMapper.updateTotalDataByNotWorkOverTime(overTimeList.get(i).getJobId(),
							totalData.getTotalNotWorkOverTime()+overTimeList.get(i).getOverTime()/30);
				}
			}
		}
	}
	
	//��ѯĳ����ÿ��Ա��ȱ�ڴ�����
	@Override
	public List<TotalData> selectAllSumAbsenceByInDate(String year, String month) 
	{
		//�����ĳ�µĹ���������
		int workDay=workingCalendarMapper.CountWorkSum(year,month);
		
		//��������յ�����
		String[] workingList=workingCalendarMapper.selectWorkDate(year, month);
		
		//���ÿ��Ա�����������մ򿨴���,ȱ�������ڹ�����������ȥ�򿨴���
		List<Attendance> workSumList=attendanceMapper.countSumWorkDayByInDate(workingList);
		
		for(int i = 0; i < workSumList.size(); i++) 
		{
			// ��ô�������Ľ���ʱ�䣬�������һ����ڼ��ѯ֮���м��������գ�Ϊ����������
			totalData = totalDataMapper.selectByJobId(workSumList.get(i).getJobId());
			// �ж���totalData�����Ƿ��Ѵ������ݣ�������ʹ��insert��䣬������ʹ��update���
			if (totalData == null) 
			{
				totalDataMapper.insertTotalDataByAbsence(workSumList.get(i).getJobId(),
						workDay-Integer.parseInt(workSumList.get(i).getToday()));
			} else {
				totalDataMapper.updateTotalDataByAbsence(workSumList.get(i).getJobId(),
						totalData.getTotalAbsence()+workDay-Integer.parseInt(workSumList.get(i).getToday()));
			}
		}
		
		//����һ�������ȱ������ȥ�������(���ڵ����˷���ǰ���Ѽ������������������ݿ��е��ü���)���ó���һ����ȱ����
		List<TotalData> totalDateList=totalDataMapper.selectAllDataSum();
		for(int i = 0; i < totalDateList.size(); i++) 
		{
			totalDataMapper.updateTotalDataByAbsence(totalDateList.get(i).getJobId(),
					totalDateList.get(i).getTotalAbsence()-totalDateList.get(i).getTotalVacate());
		}
		
		return totalDataMapper.selectAllDataSum();
	}
	
}
