package com.epms.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.OccupationAdjust;
import com.epms.Bean.SalaryAdjust;

@Repository
public interface OccupationAdjustMapper 
{
	//�ύְ�������
	public int insertOccupationAdjust(OccupationAdjust occupationAdjust);
	
	//����Ƿ����ظ�
	int selectIfRepeat(OccupationAdjust occupationAdjust);
	
	//���������id��ѯְλ����������Ϣ
	List<OccupationAdjust> selectAllOccupationAdjustByWriteId(@Param("pre_departmentid") String pre_departmentid,
			               @Param("state") String state,@Param("type")String type,@Param("before") int before,
			               @Param("after") int after,@Param("jobId") int jobId);
	int countByWriteId(@Param("pre_departmentid") String pre_departmentid,@Param("state") String state,
			           @Param("type")String type,@Param("jobId") int jobId);
	
	//ͨ������id��ѯ�����Ϣ
	OccupationAdjust selectOccupationAdjustByApplyId(int applyId);
	
	//�ϼ���ѯ�����ύ��ְλ��������
	List<OccupationAdjust> selectOccupationAdjustToLeader(@Param("pre_departmentid") String pre_departmentid,
			                                      @Param("state") String state,@Param("type") String type,
			                                      @Param("before") int before,@Param("after") int after,
			                                      @Param("writeOccupationId") int writeOccupationId);
	int countToLeader(@Param("pre_departmentid") String pre_departmentid,
			          @Param("state") String state,@Param("type") String type,
			          @Param("writeOccupationId") int writeOccupationId);
	
	//���ݹ��Ų�ѯ��ص�ְλ������¼
	List<OccupationAdjust> selectAllOccupationAdjustByJobId(@Param("state") String state,
			               @Param("type")String type,@Param("before") int before,
                           @Param("after") int after,@Param("jobId") int jobId);
    int countByJobId(@Param("state") String state,@Param("type")String type,@Param("jobId") int jobId);
	
}
