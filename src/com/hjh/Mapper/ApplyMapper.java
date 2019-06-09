package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Apply;

@Repository
public interface ApplyMapper 
{
	//���ݲ��ž����ύ���������
	int insertApply(Apply apply);
	
	//��ѯ���applyId
	int selectApplyMaxId();
	
	//�ܾ���鿴�����ύ��ȫ������������Ϣ
	List<Apply> selectApplyToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
		                                  @Param("applytype_name")String applytype_name,@Param("before") int before,
		                                  @Param("after") int after);
	int countToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
	                        @Param("applytype_name")String applytype_name);
	
	//���²鿴�ܾ����ύ��ȫ������������Ϣ
	List<Apply> selectApplyToBoard(@Param("department_id")String department_id,@Param("state")String state,
		                           @Param("applytype_name")String applytype_name,@Param("before") int before,
		                           @Param("after") int after);
	int countToBoard(@Param("department_id")String department_id,@Param("state")String state,
	                 @Param("applytype_name")String applytype_name);
	
	//������������
	int updateApply(Apply apply);
	
	//��������id��ѯ����������Ϣ
	Apply selectApplyById(int id);
	
	//���������id��ѯ����������Ϣ
	List<Apply> selectAllApplyByWriteId(@Param("department_id")String department_id,@Param("state")String state,
			    @Param("applytype_name")String applytype_name,@Param("before") int before,@Param("after") int after,
			    @Param("jobId") int jobId);
	int countByWriteId(@Param("department_id")String department_id,@Param("state")String state,
			           @Param("applytype_name")String applytype_name,@Param("jobId") int jobId);
	
	
	//ɾ��id���ļ�¼
	int delectApplyMaxId();
	
	//�ж��Ƿ��ظ��ύ
	int checkIfRepeat(Apply apply);
}
