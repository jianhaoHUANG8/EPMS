package com.epms.Mapper;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Resume;

@Repository
public interface ResumeMapper
{
   //�������
   int insertResume(Resume resume);
   
   //���ҳ���������id����Ϊ���²���ļ���id
   int selectMaxId();
   
   //��˼���
   public int updateAllResume(Resume resume);
   public int updateResumeNotAll(Resume resume);
   
   //ͨ������id����ѯ������Ϣ
 	public Resume selectResumeById(int id);
   

}
