package com.epms.Service;

import java.util.List;

import com.epms.Bean.Personalinfo;

public interface PersonalinfoService
{
	//���ݹ��Ų�ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	public Personalinfo selectPersonalByIdNotEducation(int jobId);
	
	//���ݹ��Ų�ѯ������Ϣ
	public Personalinfo selectPersonalById(int jobId);
	
	//����������ѯ������Ϣ(����ѧ�����ų���û��ѧ����Ȼ��ͷ��ؿ�)
	public Personalinfo selectPersonalByNameNotEducation(String name);
	
	//����������ѯ������Ϣ
    public Personalinfo selectPersonalByName(String name);
    
    //����id��������ѯ������Ϣ
    public List<Personalinfo> selectPersonByIdOrName (Personalinfo personal);
    
    //���ݲ���id��ѯ������Ϣ
    public List<Personalinfo> selectPersonalByDepartmentId (Personalinfo personal);
    
    //�޸ĸ�����Ϣ
    String updatePersonal (Personalinfo personal);
    
    //��ѯ������Ա��Ϣ
    public List<Personalinfo> selectAllPersonal (String occupation_id,String job_id,String name,
    		String department_id,int before,int after,int jobId);
    public int count (String occupation_id,String job_id,String name,String department_id,int jobId);
}
