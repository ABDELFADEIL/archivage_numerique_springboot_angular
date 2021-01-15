package com.archive.service;

import com.archive.entity.ContractEntity;

import java.util.Map;

public interface IContractService {

    ContractEntity findById(Integer contractId);
}
