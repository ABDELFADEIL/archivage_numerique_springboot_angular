package com.archive.controller;


import com.archive.dto.AccountDto;
import com.archive.dto.ContractDto;
import com.archive.entity.AccountEntity;
import com.archive.entity.ContractEntity;
import com.archive.service.IAccountService;
import com.archive.service.IContractService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contract")
@CrossOrigin("*")
public class ContractController {

    private final IContractService contractService;

    public ContractController(IContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping(value = "/add", consumes = {"application/json"})
    public ContractEntity add(
            @RequestBody ContractDto contractDto
    )
    {
        return contractService.add(contractDto);
    }

    @PutMapping(value = "/update", consumes = {"application/json"})
    public ContractEntity updateAccount(
            @RequestBody ContractDto contractDto
    )
    {
        return contractService.update(contractDto);
    }

}
