package com.epms.Service;

import java.util.List;

import com.epms.Bean.Attendance;
import com.epms.Bean.Contract;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;

public interface ContractService
{
	//���ݹ��Ų�ѯ�Ͷ���ͬ��Ϣ
	public List<Contract> selectContractById(String startDate,String contractType,int before,int after,int jobId);	
    public int count (String startDate,String contractType,int jobId);
	
	//���ݺ�ͬ��ʼʱ���ѯ�Ͷ���ͬ��Ϣ
	public List<Contract> selectContractByDate(Contract contract);
	
}
