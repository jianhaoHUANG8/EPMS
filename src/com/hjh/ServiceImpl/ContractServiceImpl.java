package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Contract;
import com.hjh.Bean.Partyinfo;
import com.hjh.Bean.Personalinfo;
import com.hjh.Conventer.DateConverter;
import com.hjh.Mapper.ContractMapper;
import com.hjh.Mapper.PartyinfoMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.ContractService;
import com.hjh.Service.PartyinfoService;
import com.hjh.Service.PersonalinfoService;

@Service("contractService")
public class ContractServiceImpl implements ContractService
{
	@Autowired
	private ContractMapper contractMapper;

	//���ݹ��Ų�ѯ�Ͷ���Ϣ
	@Override
	public List<Contract> selectContractById(String startDate,String contractType,int before, int after, int jobId) 
	{
		return contractMapper.selectContractById(startDate, contractType, before, after, jobId);
	}

	@Override
	public int count(String startDate,String contractType,int jobId) {
		return contractMapper.count(startDate, contractType, jobId);
	}
	
	//���ݺ�ͬ��ʼʱ���ѯ�Ͷ���ͬ��Ϣ
	@Override
	public List<Contract> selectContractByDate(Contract contract) 
	{
		return contractMapper.selectContractByDate(contract);
	}
	
}
