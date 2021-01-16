package com.archive.service;

import com.archive.dto.ContractDto;
import com.archive.entity.ContractEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IContractService {

    ContractEntity findById(Integer contractId);
    ContractEntity add(ContractDto contractDto);
    ContractEntity update(ContractDto contractDto);
    boolean delete(Integer contractId);

    Set<ContractEntity> getContractsByContractNumberContains(String contract_number);
    Set<ContractEntity> getContractsByClientNameContains(String client_name);
    String getMaxContractNumber();
    Set<ContractEntity> findContractByEventStatusEventDateBeforAndDateAfter(String status, LocalDate dateAfter, LocalDate dateBefor);
    String createNewContractNumber();
    Set<ContractEntity> getContractsByClientNameAndContractNumberContains(String client_name, String contract_number);
}
