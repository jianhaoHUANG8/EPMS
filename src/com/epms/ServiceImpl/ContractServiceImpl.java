package com.epms.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Contract;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Conventer.DateConverter;
import com.epms.Mapper.ContractMapper;
import com.epms.Mapper.PartyinfoMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.ContractService;
import com.epms.Service.PartyinfoService;
import com.epms.Service.PersonalinfoService;

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
