package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Personalinfo;

@Repository
public interface PersonalinfoMapper 
{
	//���ݹ��Ų�ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	Personalinfo selectPersonalByIdNotEducation(int jobId);
	
	//���ݹ��Ų�ѯ������Ϣ
	Personalinfo selectPersonalById(int jobId);
	
	//����������ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	Personalinfo selectPersonalByNameNotEducation(String name);
	        
	//����������ѯ������Ϣ
    Personalinfo selectPersonalByName(String name);
    
    //����������id��ѯ������Ϣ
    List<Personalinfo> selectPersonByIdOrName(Personalinfo personalinfo);
       
    //���ݲ���id��ѯ������Ϣ
    List<Personalinfo> selectPersonalByDepartmentId (Personalinfo personal);
    
    //�޸ĸ�����Ϣ
    int updatePersonal (Personalinfo personal);
    
    //��ѯ������Ա��Ϣ
    List<Personalinfo> selectAllPersonal (@Param("occupation_id")String occupation_id,
    		   @Param("job_id")String job_id,@Param("name")String name,
    		   @Param("department_id")String department_id,@Param("before") int before,
    		   @Param("after") int after);
    int count (@Param("occupation_id")String occupation_id,@Param("job_id")String job_id,
    		   @Param("name")String name,@Param("department_id")String department_id);
    
    
    //���ž����ѯ������Ա��Ϣ
    List<Personalinfo> selectAllPersonalToManager (@Param("occupation_id")String occupation_id,
    		   @Param("job_id")String job_id,@Param("name")String name,
    		   @Param("department_id")String department_id,@Param("before") int before,
    		   @Param("after") int after,@Param("managerDepartmentId") int managerDepartmentId);
    int countToManager (@Param("occupation_id")String occupation_id,@Param("job_id")String job_id,
    		   @Param("name")String name,@Param("department_id")String department_id,
    		   @Param("managerDepartmentId") int managerDepartmentId);
    
    //�޸ĸ�����Ϣ�Ĳ��ź�ְλ
    int updatePersonalByOccupationAdjust(@Param("to_occupationid")int to_occupationid,
    		                             @Param("to_departmentid")int to_departmentid,
    		                             @Param("jobId")int jobId);
    
    //ʵϰ��ת��
    int updatePersonalToEmployee(@Param("jobId")int jobId);
    
}
