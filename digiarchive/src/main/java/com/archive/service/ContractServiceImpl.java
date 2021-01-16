package com.archive.service;


import com.archive.dao.ContractRepository;
import com.archive.dto.ContractDto;
import com.archive.entity.ContractEntity;
import com.archive.entity.CustomerEntity;
import com.archive.entity.EventStatus;
import com.archive.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ContractServiceImpl implements IContractService{

    private final ContractRepository contractRepository;
    private final ICustomerService customerService;
    private final IUserService userService;


    public ContractServiceImpl(ContractRepository contractRepository, ICustomerService customerService, IUserService userService) {
        this.contractRepository = contractRepository;
        this.customerService = customerService;
        this.userService = userService;
    }


    @Override
    public ContractEntity findById(Integer contractId) {
        return contractRepository.findById(contractId).get();
    }


    @Override
    public ContractEntity add(ContractDto contractDto) {
        String contract_number = createNewContractNumber();
        UserEntity user = userService.getAuthentificatedUser();
        CustomerEntity customer = customerService.findById(contractDto.getCustomerId());

        ContractEntity contractEntity = new ContractEntity(contractDto.getContract_id_type_code(), contractDto.getContract_id_type_label(),
                customer, contract_number, LocalDate.now(), user.getId(), EventStatus.START_CUSTOMER_RELATIONSHIP);

        return contractRepository.save(contractEntity);
    }

    @Override
    public ContractEntity update(ContractDto contractDto) {

        ContractEntity contract = findById(contractDto.getId());
        if (contract == null) throw new RuntimeException( "id does not existe");

        if (contract.getContract_id_type_code() != contractDto.getContract_id_type_code()){
            contract.setContract_id_type_code(contractDto.getContract_id_type_code());
        }
        if (contract.getContract_id_type_label() != contractDto.getContract_id_type_label()){
            contract.setContract_id_type_label(contractDto.getContract_id_type_label());
        }
        if (contract.getStatus() != contractDto.getStatus()){
            contract.setStatus(contractDto.getStatus());
        }

        return contractRepository.save(contract);
    }

    @Override
    public boolean delete(Integer contractId) {
        try {
            contractRepository.deleteById(contractId);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public Set<ContractEntity> getContractsByContractNumberContains(String contract_number) {
        return contractRepository.findByContract_numberContains(contract_number);
    }

    @Override
    public Set<ContractEntity> getContractsByClientNameContains(String client_name) {
        return contractRepository.getContractsByClientNameContains(client_name);
    }


    @Override
    public Set<ContractEntity> findContractByEventStatusEventDateBeforAndDateAfter(String status, LocalDate dateAfter, LocalDate dateBefor) {
        return contractRepository.findContractsByEventStatusAndEventDateAfterAndDateBefor(status, dateAfter, dateBefor);
    }

    @Override
    public Set<ContractEntity> getContractsByClientNameAndContractNumberContains(String client_name, String contract_number) {
        return contractRepository.getContractsByClientNameAndContractNumberContains(client_name, contract_number);
    }

    @Override
    public String getMaxContractNumber() {
        return contractRepository.findMaxContractNumber();
    }

    @Override
    public String createNewContractNumber() {
        System.out.println("contract_number_pre//");
        String contract_number_pre = getMaxContractNumber();
        System.out.println(contract_number_pre);
        long contract_number =  Long.parseLong(contract_number_pre);
        System.out.println(contract_number);
        long new_contract_number = contract_number + 1;
        System.out.println(new_contract_number);
        String contract_number_nex = "00000000000".substring(String.valueOf(new_contract_number).length()+1)+new_contract_number;
        System.out.println(contract_number_nex);
        return contract_number_nex;
    }



}
