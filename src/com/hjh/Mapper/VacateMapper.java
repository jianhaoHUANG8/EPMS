package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Vacate;

@Repository
public interface VacateMapper 
{
	//���������Ϣ
	public int insertVacate(Vacate vacate); 
	
	//���ݹ��Ų�ѯȫ����ټ�¼
	public List<Vacate> selectAllVacateApplyByJobId (@Param("state") String state,@Param("type")String type,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int count (@Param("state") String state,@Param("type")String type,@Param("jobId")int jobId);
	
	//��ѯ�Ƿ����ظ��������Ϣ
	public int selectIfRepeat (Vacate vacate);
	
	//���ž����ѯȫ�������ύ���������
	List<Vacate> selectVacateToManager(@Param("department_id")String department_id,@Param("state")String state,
			                           @Param("type")String type,@Param("before") int before,
			                           @Param("after") int after,@Param("jobId")int jobId);
	int countToManager(@Param("department_id")String department_id,@Param("state")String state,
                       @Param("type")String type,@Param("jobId")int jobId);
	
	//�ܾ����ѯȫ�����ž����ύ���������
	List<Vacate> selectVacateToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
                                            @Param("type")String type,@Param("before") int before,
                                            @Param("after") int after);
	int countToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
                            @Param("type")String type);
	
	//���²�ѯȫ���ܾ����ύ���������
	List<Vacate> selectVacateToBoard(@Param("department_id")String department_id,@Param("state")String state,
                                     @Param("type")String type,@Param("before") int before,
                                     @Param("after") int after);
	int countToBoard(@Param("department_id")String department_id,@Param("state")String state,
                     @Param("type")String type);
	
	//�����������
	int updateVacate(Vacate vacate);
	
	//ͨ������id��ѯ��Ϣ
	Vacate selectVacateById(int id);
	
	//���ݹ��Ų�ѯ��ͨ����ȫ����ټ�¼
	public List<Vacate> selectPassVacateByJobId (@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int countPassVacateByJobId (int jobId);
	
	//���ٴ���
	int cancelVacateApply(Vacate vacate);
	
	//��ѯȫ��Ա���ĵ��������������ٿ�ʼʱ�����ٽ���ʱ�䶼Ϊ����ʱ��
	List<Vacate> countVacateSumBySameDate(@Param("year") String year,@Param("month") String month);
	
	//��ѯȫ��Ա���ĵ��������������ٿ�ʼʱ��Ϊ����,������ʱ�䲻Ϊ���£�
	List<Vacate> countVacateSumByStartIsMonth(@Param("year") String year,@Param("month") String month);
	
	//��ѯȫ��Ա���ĵ��������������ٽ���ʱ��Ϊ����,����ʼʱ�䲻Ϊ���£�
	List<Vacate> countVacateSumByFinishIsMonth(@Param("year") String year,@Param("month") String month);
	
	//���ݹ��Ų�ѯȫ��ͬ����������Ŀ�ʼʱ��ͽ���ʱ��
	public List<Vacate> selectVacateStartAndFinishByJobId (@Param("jobId")int jobId);
	
	
	
}
