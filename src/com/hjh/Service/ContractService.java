package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.Contract;
import com.hjh.Bean.Partyinfo;
import com.hjh.Bean.Personalinfo;

public interface ContractService
{
	//���ݹ��Ų�ѯ�Ͷ���ͬ��Ϣ
	public List<Contract> selectContractById(String startDate,String contractType,int before,int after,int jobId);	
    public int count (String startDate,String contractType,int jobId);
	
	//���ݺ�ͬ��ʼʱ���ѯ�Ͷ���ͬ��Ϣ
	public List<Contract> selectContractByDate(Contract contract);
	
}
